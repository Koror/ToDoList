package com.koror.app.dao;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
        try {
            System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
            final int indexGroup = scanner.nextInt();
            final Group group = groupList.get(indexGroup);
            group.getTaskList().add(new Task(scanner.next(), scanner.nextLine()));
            groupList.set(indexGroup, group);
        } catch (InputMismatchException exception) {
            scanner = new Scanner(System.in);
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            scanner = new Scanner(System.in);
            System.out.println("Wrong index of group");
        }
    }

    public void completeTask() {
        try {
            System.out.println("Input index group and task");
            final int indexGroup = scanner.nextInt();
            final int indexTask = scanner.nextInt();
            scanner.nextLine();
            final Group group = groupList.get(indexGroup);
            final Task task = group.getTaskList().get(indexTask);
            task.complete();
            group.getTaskList().set(indexTask, task);
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception)
        {
            scanner = new Scanner(System.in);
            System.out.println("Wrong index");
        }
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
