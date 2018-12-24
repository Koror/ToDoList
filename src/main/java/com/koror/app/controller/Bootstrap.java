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

    private void register()
    {
        AbstractCommand command = new CommandAddGroup(this);
        commandMap.put(command.command(),command);
        command = new CommandAddTask(this);
        commandMap.put(command.command(),command);
        command = new CommandClearTask(this);
        commandMap.put(command.command(),command);
        command = new CommandCompleteTask(this);
        commandMap.put(command.command(), command);
        // дома продолжу
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Action: AddGroup ReadGroup  AddTask ReadTask AddGroupToTask CompleteTask ClearTask DeleteTask DeleteGroup UpdateGroup UpdateTask Exit");
            switch (scanner.nextLine()) {
                case "AddGroup":
                    groupService.getGroupRepository().addGroup(gui.addGroup());
                    break;
                case "AddTask":
                    taskService.getTaskRepository().addTask(gui.addTask());
                    break;
                case "CompleteTask":
                    taskService.getTaskRepository().completeTask(gui.completeTask(taskService.getTaskRepository().getTaskList()));
                    break;
                case "ClearTask":
                    taskService.getTaskRepository().clearTask();
                    break;
                case "DeleteTask":
                    taskService.getTaskRepository().deleteTask(gui.deleteTask(taskService.getTaskRepository().getTaskList()));
                    break;
                case "DeleteGroup":
                    groupService.getGroupRepository().deleteGroup(gui.deleteGroup(groupService.getGroupRepository().getGroupList()));
                    break;
                case "UpdateGroup":
                    groupService.getGroupRepository().updateGroup(gui.updateGroup(groupService.getGroupRepository().getGroupList()));
                    break;
                case "UpdateTask":
                    taskService.getTaskRepository().updateTask(gui.updateTask(taskService.getTaskRepository().getTaskList()));
                    break;
                case "AddGroupToTask":
                    taskService.getTaskRepository().setGroupId(gui.AddGroupToTask(taskService.getTaskRepository().getTaskList(), groupService.getGroupRepository().getGroupList()));
                    break;
                case "ReadTask":
                    gui.readAllTask();
                    break;
                case "ReadGroup":
                    gui.readAllGroup();
                    break;
                case "Exit":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    public InterfaceGUI getGUI() {
        return gui;
    }

}
