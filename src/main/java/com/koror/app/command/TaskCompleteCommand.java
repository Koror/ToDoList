package com.koror.app.command;

import com.koror.app.entity.Task;

import java.util.List;
import java.util.Scanner;

public final class TaskCompleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        try {
            final List<Task> taskList = bootstrap.getTaskRepository().getTaskList();
            System.out.println(taskList);
            System.out.println("Input index task");
            final Task task = taskList.get(Integer.parseInt(new Scanner(System.in).nextLine()));
            task.setComplete();
            bootstrap.getTaskRepository().completeTask(task);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public String command() {
        return "CompleteTask";
    }

    @Override
    public String description() {
        return "Complete task";
    }

}
