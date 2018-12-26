package com.koror.app.controller;

import com.koror.app.command.*;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Bootstrap {

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final Map<String, AbstractCommand> commandMap = new HashMap<>();

    private final Class[] classes = {GroupAddCommand.class, GroupDeleteCommand.class, GroupReadCommand.class, GroupUpdateCommand.class,
            TaskAddCommand.class, TaskClearCommand.class, TaskCompleteCommand.class, TaskDeleteCommand.class,
            TaskReadCommand.class, TaskToGroupCommand.class, TaskUpdateCommand.class};

    private void registrar(final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private void init(Class... classes) {
        try {
            for (Class<?> c : classes) {
                if (!AbstractCommand.class.isAssignableFrom(c)) {
                    System.out.println("Class is not command: " + c.getName());
                    return;
                }
                AbstractCommand command = (AbstractCommand) c.newInstance();
                command.setBootstrap(this);
                registrar(command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        init(classes);
        String action;
        do {
            System.out.println("Action: AddGroup ReadGroup  AddTask ReadTask AddGroupToTask CompleteTask ClearTask DeleteTask DeleteGroup UpdateGroup UpdateTask Exit");
            action = scanner.nextLine();
            for (String str : commandMap.keySet()) {
                if (str.equals(action)) {
                    commandMap.get(str).execute();
                }
            }
        } while (!action.equals("Exit"));
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }

}
