package com.koror.app.controller;

import com.koror.app.command.AbstractCommand;
import com.koror.app.command.help.HelpLoginCommand;
import com.koror.app.command.user.UserLoginCommand;
import com.koror.app.command.user.UserRegisterCommand;
import com.koror.app.endpoint.*;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import lombok.Getter;
import org.reflections.Reflections;

import javax.inject.Inject;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class Bootstrap {

    @Inject
    @Getter
    private UserEndpoint userEndpoint;

    @Inject
    @Getter
    private TaskEndpoint taskEndpoint;

    @Inject
    @Getter
    private GroupEndpoint groupEndpoint;

    @Inject
    @Getter
    private SessionEndpoint sessionEndpoint;

    @Getter
    private final Map<String, AbstractCommand> commandUserMap = new HashMap<>();

    @Getter
    private final Map<String, AbstractCommand> commandLoginMap = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private final AbstractCommand[] loginCommands = {
            new HelpLoginCommand(),
            new UserRegisterCommand(),
            new UserLoginCommand()};

    private SessionDTO session;

    private void registerCommand(Map<String, AbstractCommand> commandMap, final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private Set<Class<? extends AbstractCommand>> userCommands() {
        final Reflections reflections = new Reflections("com.koror.app.command");
        return reflections.getSubTypesOf(AbstractCommand.class);
    }

    private void initUserCommand(final Set<Class<? extends AbstractCommand>> classes) throws ReflectiveOperationException, MissingCommandException {
        if (classes.size() == 0) throw new MissingCommandException();
        for (final Class c : classes) {
            if (commandNotAssignable(c)) continue;
            final AbstractCommand command = (AbstractCommand) c.newInstance();
            command.setBootstrap(this);
            registerCommand(commandUserMap, command);
        }
    }

    private void initLoginCommand(AbstractCommand[] commands) throws MissingCommandException {
        for (AbstractCommand command : commands) {
            command.setBootstrap(this);
            registerCommand(commandLoginMap, command);
        }
    }

    private boolean commandNotAssignable(final Class c) throws ReflectiveOperationException {
        if (!AbstractCommand.class.isAssignableFrom(c)) {
            System.out.println("Class is not command: " + c.getName());
            return true;
        }
        AbstractCommand command = (AbstractCommand) c.newInstance();
        return command instanceof UserLoginCommand
                || command instanceof UserRegisterCommand
                || command instanceof HelpLoginCommand;
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

    public void startClient() throws ReflectiveOperationException {
        initUserCommand(userCommands());
        initLoginCommand(loginCommands);
        String action = "";
        System.out.println("Action: Help, Exit");
        do {
            if (session == null)
                action = startCommand(commandLoginMap);
            else
                action = startCommand(commandUserMap);
        } while (!action.equals("Exit"));
    }

    public Integer nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new WrongInputException("Wrong input", e);
        }
    }

    public String nextLine() {
        final String input = scanner.nextLine();
        if (input.equals("")) throw new WrongInputException("Wrong input");
        return input;
    }

    public TaskDTO getTaskByList(List<TaskDTO> taskList) {
        int indexTask = 0;
        for (TaskDTO task : taskList) {
            System.out.println(indexTask + ": " + task.getName());
            indexTask++;
        }
        System.out.println("Input index task");
        final int inputIndex = nextInt();
        return taskList.get(inputIndex);
    }

    public UserDTO getUserByList(List<UserDTO> userList) {
        int indexUser = 0;
        for (UserDTO user : userList) {
            System.out.println(indexUser + ": " + user.getLogin());
            indexUser++;
        }
        System.out.println("Input index user");
        final int inputIndex = nextInt();
        return userList.get(inputIndex);
    }

    public GroupDTO getGroupByList(List<GroupDTO> groupList) {
        int indexUser = 0;
        for (GroupDTO group : groupList) {
            System.out.println(indexUser + ": " + group.getName());
            indexUser++;
        }
        System.out.println("Input index group");
        final int inputIndex = nextInt();
        return groupList.get(inputIndex);
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        session.setIp(getIp());
        this.session = session;
    }

    public void deleteSession(){
        session = null;
    }

    public String getIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
