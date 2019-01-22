package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.api.repository.*;
import com.koror.app.command.AbstractCommand;
import com.koror.app.repository.*;
import com.koror.app.util.DatabaseConnection;
import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import com.koror.app.service.*;
import com.koror.app.util.HibernateFactory;
import lombok.Getter;
import org.reflections.Reflections;

import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public final class Bootstrap implements IBootstrap {

    private final IUserRepository userRepository = new UserRepository();

    private final ITaskRepository taskRepository = new TaskRepository();

    private final IGroupRepository groupRepository = new GroupRepository();

    private final ISessionRepository sessionRepository = new SessionRepository();

    private final IAssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();

    private final IAssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();

    @Getter
    private final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);

    @Getter
    private final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);

    @Getter
    private final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

    @Getter
    private final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

    @Getter
    private final UserService userService = new UserService(userRepository);

    @Getter
    private final SessionService sessionService = new SessionService(sessionRepository);

    private final Scanner scanner = new Scanner(System.in);

    @Getter
    private final Map<String, AbstractCommand> serverCommands = new HashMap<>();

    private void registerCommand(Map<String, AbstractCommand> commandMap, final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private Set<Class<? extends AbstractCommand>> userCommands() {
        final Reflections reflections = new Reflections("com.koror.app.command");
        return reflections.getSubTypesOf(AbstractCommand.class);
    }

    private void initCommand(final Set<Class<? extends AbstractCommand>> classes) throws ReflectiveOperationException, MissingCommandException {
        if (classes.size() == 0) throw new MissingCommandException();
        for (final Class c : classes) {
            if (commandNotAssignable(c)) continue;
            final AbstractCommand command = (AbstractCommand) c.newInstance();
            command.setBootstrap(this);
            registerCommand(serverCommands, command);
        }
    }

    private boolean commandNotAssignable(final Class c) throws ReflectiveOperationException {
        if (!AbstractCommand.class.isAssignableFrom(c)) {
            System.out.println("Class is not command: " + c.getName());
            return true;
        }
        return false;
    }

    private String startCommand(Map<String, AbstractCommand> commandMap) {
        try {
            String action = nextLine();
            for (String str : commandMap.keySet()) {
                if (str.equals(action)) {
                    commandMap.get(str).execute();
                }
            }
            return action;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private void defaultUserInit() {
        final User userAdmin = new User("admin", "admin");
        userAdmin.setAccess(Access.ADMIN_ACCESS);
        getUserService().add(userAdmin);
        final User userTest = new User("test", "test");
        getUserService().add(userTest);
    }

    public void startServer() throws ReflectiveOperationException {
        Endpoint.publish("http://localhost:8080/TaskEndpoint?WSDL", new TaskEndpoint(this));
        Endpoint.publish("http://localhost:8080/UserEndpoint?WSDL", new UserEndpoint(this));
        Endpoint.publish("http://localhost:8080/GroupEndpoint?WSDL", new GroupEndpoint(this));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?WSDL", new SessionEndpoint(this));
        initCommand(userCommands());
        String action = "";
        System.out.println("Action: Help, Exit");
        do {
            action = startCommand(serverCommands);
        } while (!action.equals("Exit"));
        HibernateFactory.sessionFactory.close();
        DatabaseConnection.closeConnection();
    }

    @Override
    public Integer nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new WrongInputException("Wrong input", e);
        }
    }

    @Override
    public String nextLine() {
        final String input = scanner.nextLine();
        if (input.equals("")) throw new WrongInputException("Wrong input");
        return input;
    }

}
