package com.koror.app.service;

import com.koror.app.gui.CommandLineGUI;

import java.util.Scanner;

public class ToDoListService {

    private final GroupService groupService = new GroupService();

    private final TaskService taskService = new TaskService(groupService.getGroupList());

    public static final CommandLineGUI gui = new CommandLineGUI();

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask ClearTask DeleteTask DeleteGroup UpdateGroup UpdateTask Exit");
            switch (scanner.nextLine()) {
                case "AddGroup":
                    groupService.addGroup();
                    break;
                case "AddTask":
                    taskService.addTask();
                    break;
                case "CompleteTask":
                    taskService.completeTask();
                    break;
                case "ClearTask":
                    taskService.clearTask();
                    break;
                case "DeleteTask":
                    taskService.deleteTask();
                    break;
                case "DeleteGroup":
                    groupService.deleteGroup();
                    break;
                case "UpdateGroup":
                    groupService.updateGroup();
                    break;
                case "UpdateTask":
                    taskService.updateTask();
                    break;
                case "ReadAll":
                    taskService.readAll();
                    break;
                case "Exit":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

}
