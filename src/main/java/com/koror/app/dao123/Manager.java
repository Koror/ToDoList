package com.koror.app.dao123;

import com.koror.app.entity123.Group;
import com.koror.app.entity123.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Manager {

    private ArrayList<Group> groupList = new ArrayList<>();

    private Scanner scanner = new Scanner(System.in);

    public ArrayList<Group> getGroupList() {
        return groupList;
    }

    public void addGroup() {
        System.out.println("Input name group");
        groupList.add(new Group(scanner.nextLine()));
    }

    public void addTask() {
        System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
        final int indexGroup = scanner.nextInt();
        Group group = groupList.get(indexGroup);
        group.getTaskList().add(new Task(scanner.next(), scanner.nextLine()));
        groupList.set(indexGroup, group);
    }

    public void completeTask() {
        System.out.println("Input index group and task");
        final int indexGroup = scanner.nextInt();
        final int indexTask = scanner.nextInt();
        scanner.nextLine();
        Group group = groupList.get(indexGroup);
        Task task = group.getTaskList().get(indexTask);
        task.complete();
        group.getTaskList().set(indexTask, task);
    }

    public void clear() {
        for (Group group : getGroupList()) {
            Iterator<Task> taskIterator = group.getTaskList().iterator();
            while (taskIterator.hasNext()) {
                if (taskIterator.next().isComplete())
                    taskIterator.remove();
            }
        }
    }

    public void readAll() {
        int indexGroup = 0;
        for (Group group : getGroupList()) {
            int indexTask = 0;
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (Task task : group.getTaskList()) {
                System.out.println("   " + indexTask + " [" + task.toString() + "]");
                indexTask++;
            }
            indexGroup++;
        }
    }

}
