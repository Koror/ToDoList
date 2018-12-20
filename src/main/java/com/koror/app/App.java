package com.koror.app;

import com.koror.app.dao123.Manager;
import com.koror.app.entity123.Group;
import com.koror.app.entity123.Task;

import java.util.Scanner;

public class App {

    private final static Manager manager = new Manager();

    public static void main(String[] args) {
        start();
    }

    private static void start() {

        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask Clear Exit");
            switch (scanner.nextLine()) {
                case "AddGroup":
                    manager.addGroup();
                    break;
                case "ReadAll":
                    manager.readAll();
                    break;
                case "AddTask":
                    manager.addTask();
                    break;
                case "CompleteTask":
                    manager.completeTask();
                    break;
                case "Clear":
                    manager.clear();
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
        manager.getGroupList().add(group);
        group = new Group("Week");
        manager.getGroupList().add(group);

        Task task = new Task("Shop", "MEDIUM");
        group = manager.getGroupList().get(0);
        group.getTaskList().add(task);
        manager.getGroupList().set(0, group);

        task = new Task("Read", "MEDIUM");
        group = manager.getGroupList().get(0);
        group.getTaskList().add(task);
        manager.getGroupList().set(0, group);

        task = new Task("Work", "HIGH");
        group = manager.getGroupList().get(1);
        group.getTaskList().add(task);
        manager.getGroupList().set(1, group);

        group = manager.getGroupList().get(0);
        task = group.getTaskList().get(1);
        task.complete();
        group.getTaskList().set(1, task);

        group = manager.getGroupList().get(1);
        task = group.getTaskList().get(0);
        task.complete();
        group.getTaskList().set(0, task);
    }
}
