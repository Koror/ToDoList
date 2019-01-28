package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.api.repository.*;
import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.*;
import com.koror.app.service.*;
import com.koror.app.util.AppConfig;
import com.koror.app.util.HibernateFactory;
import lombok.Getter;
import org.reflections.Reflections;

import javax.xml.ws.Endpoint;
import java.io.IOException;
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
    private final AssigneeTaskService assigneeTaskService;

    @Getter
    private final AssigneeGroupService assigneeGroupService;

    @Getter
    private final GroupService groupService;

    @Getter
    private final TaskService taskService;

    @Getter
    private final UserService userService;

    @Getter
    private final SessionService sessionService;

    @Getter
    private final Map<String, AbstractCommand> serverCommands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private final HibernateFactory hibernateFactory;

    public Bootstrap() throws IOException {
        AppConfig.init();
        hibernateFactory = new HibernateFactory();
        assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository, hibernateFactory.getEntityManagerFactory());
        assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository, hibernateFactory.getEntityManagerFactory());
        groupService = new GroupService(groupRepository, assigneeGroupRepository, taskRepository, assigneeTaskRepository, hibernateFactory.getEntityManagerFactory());
        taskService = new TaskService(taskRepository, assigneeTaskRepository, hibernateFactory.getEntityManagerFactory());
        userService = new UserService(userRepository, assigneeTaskRepository, hibernateFactory.getEntityManagerFactory());
        sessionService = new SessionService(sessionRepository, hibernateFactory.getEntityManagerFactory());
    }

    private void registerCommand(final Map<String, AbstractCommand> commandMap, final AbstractCommand command) {
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

    private boolean commandNotAssignable(final Class c) {
        if (!AbstractCommand.class.isAssignableFrom(c)) {
            System.out.println("Class is not command: " + c.getName());
            return true;
        }
        return false;
    }

    private String startCommand(final Map<String, AbstractCommand> commandMap) {
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

    public void defaultUserInit() {
        final User userAdmin = new User("admin", "admin");
        userAdmin.setAccess(Access.ADMIN_ACCESS);
        getUserService().add(userAdmin);
        final User userTest = new User("test", "test");
        getUserService().add(userTest);
    }

    public void startServer() throws ReflectiveOperationException {
        Endpoint.publish("http://localhost:8080/TaskEndpoint?WSDL", new TaskEndpoint(getTaskService(), getSessionService()));
        Endpoint.publish("http://localhost:8080/UserEndpoint?WSDL", new UserEndpoint(getUserService(), getSessionService()));
        Endpoint.publish("http://localhost:8080/GroupEndpoint?WSDL", new GroupEndpoint(getGroupService(), getSessionService()));
        Endpoint.publish("http://localhost:8080/SessionEndpoint?WSDL", new SessionEndpoint(getSessionService()));
        initCommand(userCommands());
        String action = "";
        System.out.println("Action: Help, Exit");
        do {
            action = startCommand(serverCommands);
        } while (!action.equals("Exit"));
        close();
    }

    @Override
    public void close(){
        hibernateFactory.close();
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
