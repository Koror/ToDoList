package com.koror.app.controller;

import com.koror.app.commander.*;
import com.koror.app.repository.GroupRepository;
import com.koror.app.repository.TaskRepository;
import com.koror.app.service.GroupService;
import com.koror.app.service.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bootstrap {

    private final GroupRepository groupRepository = new GroupRepository();

    private final TaskRepository taskRepository = new TaskRepository();

    public final GroupService groupService = new GroupService(groupRepository, taskRepository);

    public final TaskService taskService = new TaskService(taskRepository, groupRepository);

    public final InterfaceGUI gui = new CommandLineGUI(taskService, groupService);

    private final Map<String, AbstractCommand> commandMap = new HashMap<>();

    private void register() {
        AbstractCommand command = new CommandAddGroup(this);
        commandMap.put(command.command(), command);
        command = new CommandAddTask(this);
        commandMap.put(command.command(), command);
        command = new CommandClearTask(this);
        commandMap.put(command.command(), command);
        command = new CommandCompleteTask(this);
        commandMap.put(command.command(), command);
        command = new CommandAddGroupToTask(this);
        commandMap.put(command.command(), command);
        command = new CommandDeleteGroup(this);
        commandMap.put(command.command(), command);
        command = new CommandDeleteTask(this);
        commandMap.put(command.command(), command);
        command = new CommandReadGroup(this);
        commandMap.put(command.command(), command);
        command = new CommandReadTask(this);
        commandMap.put(command.command(), command);
        command = new CommandUpdateGroup(this);
        commandMap.put(command.command(), command);
        command = new CommandUpdateTask(this);
        commandMap.put(command.command(), command);
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        register();
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

}
