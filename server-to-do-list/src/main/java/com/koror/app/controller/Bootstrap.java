package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.api.repository.*;
import com.koror.app.command.AbstractCommand;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import com.koror.app.service.*;
import com.koror.app.util.Transaction;
import lombok.Getter;
import org.reflections.Reflections;

import javax.xml.ws.Endpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public final class Bootstrap implements IBootstrap {

    private IUserRepository userRepository;

    private ITaskRepository taskRepository;

    private IGroupRepository groupRepository;

    private ISessionRepository sessionRepository;

    private IAssigneeGroupRepository assigneeGroupRepository;

    private IAssigneeTaskRepository assigneeTaskRepository;

    @Getter
    private AssigneeTaskService assigneeTaskService;

    @Getter
    private AssigneeGroupService assigneeGroupService;

    @Getter
    private GroupService groupService;

    @Getter
    private TaskService taskService;

    @Getter
    private UserService userService;

    @Getter
    private SessionService sessionService;

    private final Scanner scanner = new Scanner(System.in);

    @Getter
    private final Map<String, AbstractCommand> serverCommands = new HashMap<>();

    public Bootstrap() throws IOException {
        DatabaseConnection.setConnection();
        Transaction.openSqlSession();
        userRepository = Transaction.getSqlSession().getMapper(IUserRepository.class);
        taskRepository = Transaction.getSqlSession().getMapper(ITaskRepository.class);
        groupRepository = Transaction.getSqlSession().getMapper(IGroupRepository.class);
        sessionRepository = Transaction.getSqlSession().getMapper(ISessionRepository.class);
        assigneeGroupRepository = Transaction.getSqlSession().getMapper(IAssigneeGroupRepository.class);
        assigneeTaskRepository = Transaction.getSqlSession().getMapper(IAssigneeTaskRepository.class);

        userService = new UserService(userRepository);
        sessionService = new SessionService(sessionRepository);
        assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);
        assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);
        taskService = new TaskService(taskRepository, assigneeTaskService);
        groupService = new GroupService(groupRepository, assigneeGroupService);
    }

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
        userAdmin.setAccess(Access.ADMIN);
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
