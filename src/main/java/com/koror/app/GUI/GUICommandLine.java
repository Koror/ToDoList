package com.koror.app.GUI;

import com.koror.app.dao.Manager;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GUICommandLine implements Manager {

    private Scanner scanner = new Scanner(System.in);

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
        try {
            System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
            indexGroup = scanner.nextInt();
            nameTask = scanner.next();
            priority = scanner.nextLine();
        } catch (
                InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index of group");
        } finally {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void completeTask() {
        try {
            System.out.println("Input index group and task");
            indexGroup = scanner.nextInt();
            indexTask = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        } finally {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void deleteTask() {
        try {
            System.out.println("Input index group and task");
            indexGroup = scanner.nextInt();
            indexTask = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        } finally {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void deleteGroup() {
        try {
            System.out.println("Input index group");
            indexGroup = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        } finally {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void updateTask() {
        try {
            System.out.println("Input index group | index task | task name | priority{LOW MEDIUM HIGH}");
            indexGroup = scanner.nextInt();
            indexTask = scanner.nextInt();
            nameTask = scanner.next();
            priority = scanner.nextLine();
        } catch (
                InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index of group");
        } finally {
            scanner = new Scanner(System.in);
        }
    }

    @Override
    public void readAll(ArrayList<Group> groupList) {
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

    public int getIndexGroup() {
        return indexGroup;
    }

    public int getIndexTask() {
        return indexTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public String getPriority() {
        return priority;
    }
}
