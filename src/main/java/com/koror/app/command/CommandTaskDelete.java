package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Task;

import java.util.List;
import java.util.Scanner;

public class CommandTaskDelete extends AbstractCommand {

    public CommandTaskDelete(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        try {
            final List<Task> taskList = bootstrap.getTaskRepository().getTaskList();
            System.out.println(taskList);
            System.out.println("Input index task");
            bootstrap.getTaskRepository().deleteTask(taskList.get(Integer.parseInt(new Scanner(System.in).nextLine())).getId());
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public String command() {
        return "DeleteTask";
    }

    @Override
    public String description() {
        return "Delete task";
    }

}
