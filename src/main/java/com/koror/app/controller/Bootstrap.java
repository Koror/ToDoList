package com.koror.app.controller;

import com.koror.app.api.controller.IBootstrap;
import com.koror.app.command.*;
import com.koror.app.command.data.LoadDataJsonCommand;
import com.koror.app.command.data.LoadDataSerializationCommand;
import com.koror.app.command.data.LoadDataXmlCommand;
import com.koror.app.command.help.HelpLoginCommand;
import com.koror.app.command.user.LoginCommand;
import com.koror.app.command.user.UserRegisterCommand;
import com.koror.app.entity.User;
import com.koror.app.error.MissingCommandException;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.*;
import com.koror.app.security.Authorization;
import com.koror.app.service.*;
import org.reflections.Reflections;

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

    private void registrar(Map<String, AbstractCommand> commandMap, final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private Set<Class<? extends AbstractCommand>> registerClass() {
        Reflections reflections = new Reflections("com.koror.app.command.task");
        final Set<Class<? extends AbstractCommand>> allClasses = reflections.getSubTypesOf(AbstractCommand.class);
        reflections = new Reflections("com.koror.app.command.group");
        allClasses.addAll(reflections.getSubTypesOf(AbstractCommand.class));
        reflections = new Reflections("com.koror.app.command.data");
        allClasses.addAll(reflections.getSubTypesOf(AbstractCommand.class));
        reflections = new Reflections("com.koror.app.command.user");
        allClasses.addAll(reflections.getSubTypesOf(AbstractCommand.class));
        reflections = new Reflections("com.koror.app.command.help");
        allClasses.addAll(reflections.getSubTypesOf(AbstractCommand.class));
        return allClasses;
    }

    private void initUserCommand(final Set<Class<? extends AbstractCommand>> classes) throws ReflectiveOperationException, MissingCommandException {
        if (classes.size() == 0) throw new MissingCommandException();
        for (final Class c : classes) {
            if (commandNotAssignable(c)) continue;
            final AbstractCommand command = (AbstractCommand) c.newInstance();
            command.setBootstrap(this);
            if (command instanceof LoginCommand) continue;
            if (command instanceof UserRegisterCommand) continue;
            if (command instanceof HelpLoginCommand) continue;
            registrar(commandUserMap, command);
        }
    }

    private void initLoginCommand(final Set<Class<? extends AbstractCommand>> classes) throws ReflectiveOperationException, MissingCommandException {
        if (classes.size() == 0) throw new MissingCommandException();
        for (final Class c : classes) {
            if (commandNotAssignable(c)) continue;
            final AbstractCommand command = (AbstractCommand) c.newInstance();
            command.setBootstrap(this);
            if (command instanceof LoginCommand ||
                    command instanceof UserRegisterCommand ||
                    command instanceof LoadDataJsonCommand ||
                    command instanceof LoadDataSerializationCommand ||
                    command instanceof LoadDataXmlCommand ||
                    command instanceof HelpLoginCommand)
                registrar(commandLoginMap, command);
        }
    }


    private boolean commandNotAssignable(final Class c) {
        if (!AbstractCommand.class.isAssignableFrom(c)) {
            System.out.println("Class is not command: " + c.getName());
            return true;
        }
        return false;
    }

    private String startCommand(Map<String, AbstractCommand> commandMap) {
        String action = nextLine();
        for (String str : commandMap.keySet()) {
            if (str.equals(action)) {
                try {
                    commandMap.get(str).execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return action;
    }

    private void defaultUserInit() {
        User user = new User("admin", "admin");
        getUserService().registerUser(user);
        user = new User("test", "test");
        getUserService().registerUser(user);
    }

    @Override
    public void start() throws ReflectiveOperationException {
        initUserCommand(registerClass());
        initLoginCommand(registerClass());
        defaultUserInit();
        String action;
        System.out.println("Action: Help, Exit");
        do {
            if (authorization.getLogin() == null)
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
