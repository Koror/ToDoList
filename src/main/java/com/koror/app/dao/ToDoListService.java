package com.koror.app.dao;

import com.koror.app.gui.CommandLineGUI;

import java.util.Scanner;

public class ToDoListService extends Service {

    private final GroupManager groupManager = new GroupManager();

    private final TaskManager taskManager = new TaskManager();

    private final CommandLineGUI gui = new CommandLineGUI();

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Action: AddGroup ReadAll AddTask CompleteTask Clear DeleteTask DeleteGroup UpdateGroup UpdateTask Exit");
            switch (scanner.nextLine()) {
                case "AddGroup":
                    addGroup();
                    break;
                case "ReadAll":
                    readAll();
                    break;
                case "AddTask":
                    addTask();
                    break;
                case "CompleteTask":
                    completeTask();
                    break;
                case "Clear":
                    clear();
                    break;
                case "DeleteTask":
                    deleteTask();
                    break;
                case "DeleteGroup":
                    deleteGroup();
                    break;
                case "UpdateGroup":
                    updateGroup();
                    break;
                case "UpdateTask":
                    updateTask();
                    break;
                case "Exit":
                    return;
                default:
                    System.out.println("Wrong command");
                    break;
            }
        }
    }

    @Override
    public void addGroup() {
        gui.addGroup();
        groupManager.addGroup(gui.getNameGroup());
    }

    @Override
    public void addTask() {
        gui.addTask();
        taskManager.addTask(groupManager.getGroupList(), gui.getIndexGroup(), gui.getNameTask(), gui.getPriority());
    }

    @Override
    public void completeTask() {
        gui.completeTask();
        taskManager.completeTask(groupManager.getGroupList(), gui.getIndexGroup(), gui.getIndexTask());
    }

    @Override
    public void clear() {
        taskManager.clear(groupManager.getGroupList());
    }

    @Override
    public void deleteTask() {
        gui.deleteTask();
        taskManager.deleteTask(groupManager.getGroupList(), gui.getIndexGroup(), gui.getIndexTask());
    }

    @Override
    public void deleteGroup() {
        gui.deleteGroup();
        groupManager.deleteGroup(gui.getIndexGroup());
    }

    public void updateGroup() {
        gui.updateGroup();
        groupManager.updateGroup(gui.getIndexGroup(), gui.getNameGroup());
    }

    @Override
    public void updateTask() {
        gui.updateTask();
        taskManager.updateTask(groupManager.getGroupList(), gui.getIndexGroup(), gui.getIndexTask(), gui.getNameTask(), gui.getPriority());
    }

    @Override
    public void readAll() {
        gui.readAll(groupManager.getGroupList());
    }

}
