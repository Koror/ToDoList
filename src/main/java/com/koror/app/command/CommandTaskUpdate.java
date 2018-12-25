package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Task;
import com.koror.app.enumeration.Priority;

import java.util.List;
import java.util.Scanner;

public class CommandTaskUpdate extends AbstractCommand {

    public CommandTaskUpdate(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        try {
            final List<Task> taskList = bootstrap.getTaskRepository().getTaskList();
            System.out.println("index task, task name and priority{LOW MEDIUM HIGH}");
            final Task task = taskList.get(Integer.parseInt(new Scanner(System.in).nextLine()));
            task.setText(new Scanner(System.in).nextLine());
            task.setPriority(Priority.valueOf(new Scanner(System.in).nextLine()));
            bootstrap.getTaskRepository().updateTask(task);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public String command() {
        return "UpdateTask";
    }

    @Override
    public String description() {
        return "Update task";
    }

}
