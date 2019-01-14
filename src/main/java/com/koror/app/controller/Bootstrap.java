package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.command.*;
import com.koror.app.command.data.LoadDataJsonCommand;
import com.koror.app.command.data.LoadDataSerializationCommand;
import com.koror.app.command.data.LoadDataXmlCommand;
import com.koror.app.command.help.HelpLoginCommand;
import com.koror.app.command.user.UserLoginCommand;
import com.koror.app.command.user.UserRegisterCommand;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.*;
import com.koror.app.security.Authorization;
import com.koror.app.service.*;
import org.reflections.Reflections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public final class Bootstrap implements IBootstrap {

    private final AssigneeTaskRepository assigneeTaskRepository = new AssigneeTaskRepository();

    private final AssigneeTaskService assigneeTaskService = new AssigneeTaskService(assigneeTaskRepository);

    private final AssigneeGroupRepository assigneeGroupRepository = new AssigneeGroupRepository();

    private final AssigneeGroupService assigneeGroupService = new AssigneeGroupService(assigneeGroupRepository);

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final GroupService groupService = new GroupService(groupRepository, assigneeGroupService);

    private final TaskService taskService = new TaskService(taskRepository, assigneeTaskService);

    private final UserRepository userRepository = new UserRepository();

    private final UserService userService = new UserService(userRepository);

    private Authorization authorization = new Authorization();

    private final Map<String, AbstractCommand> commandUserMap = new HashMap<>();

    private final Map<String, AbstractCommand> commandLoginMap = new HashMap<>();

    private final Scanner scanner = new Scanner(System.in);

    private final AbstractCommand[] loginCommands = {
            new HelpLoginCommand(),
            new UserRegisterCommand(),
            new UserLoginCommand(),
            new LoadDataJsonCommand(),
            new LoadDataSerializationCommand(),
            new LoadDataXmlCommand()};

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
        if ((command instanceof UserLoginCommand)
                || (command instanceof UserRegisterCommand)
                || (command instanceof HelpLoginCommand)) {
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
        User user = new User("admin", "admin");
        user.setAccess(Access.ADMIN);
        getUserService().add(user);
        user = new User("test", "test");
        getUserService().add(user);
    }

    private void loadDataFromJson() throws IOException {
        getTaskService().loadDataJson();
        getGroupService().loadDataJson();
        getUserService().loadDataJson();
        getAssigneeTaskService().loadDataJson();
        getAssigneeGroupService().loadDataJson();
    }

    @Override
    public void start() throws ReflectiveOperationException, IOException {
        initUserCommand(userCommands());
        initLoginCommand(loginCommands);
        defaultUserInit();
        //loadDataFromJson();
        String action;
        System.out.println("Action: Help, Exit");
        do {
            if (authorization.getUser() == null)
                action = startCommand(commandLoginMap);
            else
                action = startCommand(commandUserMap);
        } while (!action.equals("Exit"));
    }

    @Override
    public GroupService getGroupService() {
        return groupService;
    }

    @Override
    public TaskService getTaskService() {
        return taskService;
    }

    @Override
    public UserService getUserService() {
        return userService;
    }

    @Override
    public AssigneeGroupService getAssigneeGroupService() {
        return assigneeGroupService;
    }

    @Override
    public AssigneeTaskService getAssigneeTaskService() {
        return assigneeTaskService;
    }

    @Override
    public Authorization getAuthorization() {
        return authorization;
    }

    public Map<String, AbstractCommand> getCommandUserMap() {
        return commandUserMap;
    }

    public Map<String, AbstractCommand> getCommandLoginMap() {
        return commandLoginMap;
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
