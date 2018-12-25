package com.koror.app.controller;

import com.koror.app.command.*;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    private final Map<String, AbstractCommand> commandMap = new HashMap<>();

    private void registrar(final AbstractCommand command) {
        commandMap.put(command.command(), command);
    }

    private void init() {
//        try {
//            for (int i = 0; i < className.length; i++) {
//                Class<?> c = Class.forName(className[i]);
//                Constructor<?> cons = c.getConstructor(Bootstrap.class);
//                Object o = cons.newInstance(this);
//                registrar((AbstractCommand) o);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        registrar(new CommandGroupAdd(this));
        registrar(new CommandTaskAdd(this));
        registrar(new CommandTaskClear(this));
        registrar(new CommandTaskComplete(this));
        registrar(new CommandTaskToGroup(this));
        registrar(new CommandGroupDelete(this));
        registrar(new CommandTaskDelete(this));
        registrar(new CommandGroupRead(this));
        registrar(new CommandTaskRead(this));
        registrar(new CommandGroupUpdate(this));
        registrar(new CommandTaskUpdate(this));
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);
        init();
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
