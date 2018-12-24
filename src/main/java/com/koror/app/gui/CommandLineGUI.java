package com.koror.app.gui;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;
import java.util.Scanner;

public class CommandLineGUI implements InterfaceGUI {

    private final Scanner scanner = new Scanner(System.in);

    private int indexGroup;

    private int indexTask;

    private String nameTask;

    private String nameGroup;

    private String priority;

    @Override
    public void addGroup() {
        System.out.println("Input name group");
        nameGroup = scanner.nextLine();
    }

    @Override
    public void addTask() {
        System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
        indexGroup = Integer.parseInt(scanner.nextLine());
        nameTask = scanner.nextLine();
        priority = scanner.nextLine();
    }

    @Override
    public void completeTask() {
        System.out.println("Input index group and task");
        indexGroup = Integer.parseInt(scanner.nextLine());
        indexTask = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void deleteTask() {
        System.out.println("Input index group and task");
        indexGroup = Integer.parseInt(scanner.nextLine());
        indexTask = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void deleteGroup() {
        System.out.println("Input index group");
        indexGroup = Integer.parseInt(scanner.nextLine());
    }

    @Override
    public void updateGroup() {
        System.out.println("Input index group and name");
        indexGroup = Integer.parseInt(scanner.nextLine());
        nameGroup = scanner.nextLine();
    }

    public void updateTask() {
        System.out.println("Input index group | index task | task name | priority{LOW MEDIUM HIGH}");
        indexGroup = Integer.parseInt(scanner.nextLine());
        indexTask = Integer.parseInt(scanner.nextLine());
        nameTask = scanner.nextLine();
        priority = scanner.nextLine();
    }

    @Override
    public void readAll(List<Group> groupList) {
        int indexGroup = 0;
        for (Group group : groupList) {
            int indexTask = 0;
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (Task task : group.getTaskList()) {
                System.out.println("   " + indexTask + " [" + task.toString() + "]");
                indexTask++;
            }
            indexGroup++;
        }
    }

    @Override
    public void readAllGroup(List<Group> groupList) {
        int indexGroup = 0;
        for (Group group : groupList) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
            indexGroup++;
        }
    }

    @Override
    public void readGroup(List<Group> groupList) {
        System.out.println("Input group index");
        int indexGroup = Integer.parseInt(scanner.nextLine());
        int indexTask = 0;
        System.out.println(indexGroup + " [" + groupList.get(indexGroup).toString() + "]");
        for (Task task : groupList.get(indexGroup).getTaskList()) {
            System.out.println("   " + indexTask + " [" + task.toString() + "]");
            indexTask++;
        }
    }

    @Override
    public int getIndexGroup() {
        return indexGroup;
    }

    @Override
    public int getIndexTask() {
        return indexTask;
    }

    @Override
    public String getNameTask() {
        return nameTask;
    }

    @Override
    public String getNameGroup() {
        return nameGroup;
    }

    @Override
    public String getPriority() {
        return priority;
    }

}
