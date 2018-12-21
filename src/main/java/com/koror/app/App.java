package com.koror.app;

import com.koror.app.dao.TaskManager;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.Scanner;

public class App {

    private final static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask Clear DeleteTask DeleteGroup UpdateTask Exit");
            switch (scanner.nextLine()) {
                case "AddGroup":
                    taskManager.addGroup();
                    break;
                case "ReadAll":
                    taskManager.readAll(taskManager.getGroupList());
                    break;
                case "AddTask":
                    taskManager.addTask();
                    break;
                case "CompleteTask":
                    taskManager.completeTask();
                    break;
                case "Clear":
                    taskManager.clear();
                    break;
                case "DeleteTask":
                    taskManager.deleteTask();
                    break;
                case "DeleteGroup":
                    taskManager.deleteGroup();
                    break;
                case "UpdateTask":
                    taskManager.updateTask();
                    break;
                case "Test":
                    testWriting();
                    break;
                case "Exit":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    private static void testWriting() {
        Group group = new Group("Day");
        taskManager.getGroupList().add(group);
        group = new Group("Week");
        taskManager.getGroupList().add(group);

        Task task = new Task("Shop", "MEDIUM");
        group = taskManager.getGroupList().get(0);
        group.getTaskList().add(task);
        taskManager.getGroupList().set(0, group);

        task = new Task("Read", "MEDIUM");
        group = taskManager.getGroupList().get(0);
        group.getTaskList().add(task);
        taskManager.getGroupList().set(0, group);

        task = new Task("Work", "HIGH");
        group = taskManager.getGroupList().get(1);
        group.getTaskList().add(task);
        taskManager.getGroupList().set(1, group);

        group = taskManager.getGroupList().get(0);
        task = group.getTaskList().get(1);
        task.complete();
        group.getTaskList().set(1, task);

        group = taskManager.getGroupList().get(1);
        task = group.getTaskList().get(0);
        task.complete();
        group.getTaskList().set(0, task);
    }

}
