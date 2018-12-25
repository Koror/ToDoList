package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Task;
import com.koror.app.enumeration.Priority;

import java.util.Scanner;

public class CommandTaskAdd extends AbstractCommand {

    public CommandTaskAdd(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        System.out.println("Input task name and priority{LOW MEDIUM HIGH}");
        final String name = new Scanner(System.in).nextLine();
        final String priority = new Scanner(System.in).nextLine();
        Task task;
        try {
            task = new Task(name, Priority.valueOf(priority));
        } catch (Exception e) {
            task = new Task(name);
        }
        bootstrap.getTaskRepository().addTask(task);
    }

    @Override
    public String command() {
        return "AddTask";
    }

    @Override
    public String description() {
        return "Add new task";
    }

}
