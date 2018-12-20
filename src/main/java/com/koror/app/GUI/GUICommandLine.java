package com.koror.app.GUI;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GUICommandLine {

    private Scanner scanner = new Scanner(System.in);

    public int indexGroup;
    public int indexTask;
    public String nameTask;
    public String nameGroup;
    public String priority;

    public void addGroup() {
        System.out.println("Input name group");
        nameGroup = scanner.nextLine();
    }

    public void addTask() {
        try {
            System.out.println("Input index group | task name | priority{LOW MEDIUM HIGH}");
            indexGroup = scanner.nextInt();
            nameTask = scanner.next();
            priority = scanner.nextLine();
        } catch (
                InputMismatchException exception) {
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
            indexGroup = scanner.nextInt();
            indexTask = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            scanner = new Scanner(System.in);
            System.out.println("Wrong index");
        }
    }

    public void deleteTask() {
        try {
            System.out.println("Input index group and task");
            indexGroup = scanner.nextInt();
            indexTask = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            scanner = new Scanner(System.in);
            System.out.println("Wrong index");
        }
    }

    public void deleteGroup() {
        try {
            System.out.println("Input index group");
            indexGroup = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException exception) {
            scanner = new Scanner(System.in);
            System.out.println("Wrong index");
        }
    }

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
}
