package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupEndpoint;
import com.koror.app.endpoint.SessionEndpoint;
import com.koror.app.endpoint.TaskEndpoint;
import com.koror.app.endpoint.UserEndpoint;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.reflections.Reflections;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

@NoArgsConstructor
public final class Bootstrap implements IBootstrap {

    @Inject
    private IGroupService groupService;

    @Inject
    private ITaskService taskService;

    @Inject
    private IUserService userService;

    @Inject
    private ISessionService sessionService;

    @Inject
    private TaskEndpoint taskEndpoint;

    @Inject
    private UserEndpoint userEndpoint;

    @Inject
    private GroupEndpoint groupEndpoint;

    @Inject
    private SessionEndpoint sessionEndpoint;

    @Getter
    private final Map<String, AbstractCommand> serverCommands = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

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
            if (userService.getByLogin("admin") == null) {
                final User userAdmin = new User("admin", "admin");
                userAdmin.setAccess(Access.ADMIN_ACCESS);
                userService.add(userAdmin);
            }
            if (userService.getByLogin("test") == null) {
                final User userTest = new User("test", "test");
                userService.add(userTest);
            }
    }

    @Override
    public void startServer() throws ReflectiveOperationException {
        Endpoint.publish("http://localhost:8080/TaskEndpoint?WSDL", taskEndpoint);
        Endpoint.publish("http://localhost:8080/UserEndpoint?WSDL", userEndpoint);
        Endpoint.publish("http://localhost:8080/GroupEndpoint?WSDL", groupEndpoint);
        Endpoint.publish("http://localhost:8080/SessionEndpoint?WSDL", sessionEndpoint);
        initCommand(userCommands());
        String action = "";
        defaultUserInit();
        System.out.println("Action: Help, Exit");
        do {
            action = startCommand(serverCommands);
        } while (!action.equals("Exit"));
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
