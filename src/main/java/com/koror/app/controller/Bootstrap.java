package com.koror.app.controller;

import com.koror.app.command.*;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;
import com.koror.app.service.GroupService;
import com.koror.app.service.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Bootstrap {

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final GroupService groupService = new GroupService(groupRepository, taskRepository);

    private final TaskService taskService = new TaskService(taskRepository, groupRepository);

    private final Map<String, AbstractCommand> commandMap = new HashMap<>();

    private final Class[] classes = {
            GroupAddCommand.class, GroupDeleteCommand.class, GroupReadCommand.class,
            GroupUpdateCommand.class, TaskAddCommand.class, TaskClearCommand.class,
            TaskCompleteCommand.class, TaskDeleteCommand.class, TaskReadCommand.class,
            TaskToGroupCommand.class, TaskUpdateCommand.class};

    private final Scanner scanner = new Scanner(System.in);

    private void registrar(final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private void init(final Class... classes) throws Exception {
        for (final Class c : classes) {
            if (commandNotAssignable(c)) continue;
            final AbstractCommand command = (AbstractCommand) c.newInstance();
            command.setBootstrap(this);
            registrar(command);
        }
    }

    private boolean commandNotAssignable(Class c) {
        if (!AbstractCommand.class.isAssignableFrom(c)) {
            System.out.println("Class is not command: " + c.getName());
            return true;
        }
        return false;
    }

    public void start() throws Exception {
        final Scanner scanner = new Scanner(System.in);
        init(classes);
        String action;
        do {
            System.out.println("Action: " + commandMap.keySet() + " Exit");
            action = scanner.nextLine();
            for (String str : commandMap.keySet()) {
                if (str.equals(action)) {
                    commandMap.get(str).execute();
                }
            }
        } while (!action.equals("Exit"));
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public Integer nextInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Wrong input");
        }
        return null;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

}
