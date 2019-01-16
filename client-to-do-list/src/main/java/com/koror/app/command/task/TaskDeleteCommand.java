package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Task;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        int indexTask = 0;
        for (Task task : taskList) {
            System.out.println(indexTask+": "+task.getName());
            indexTask++;
        }
        System.out.println("Input index task");
        final int inputIndex = bootstrap.nextInt();
        bootstrap.getTaskService().deleteTask(taskList.get(inputIndex).getId(), bootstrap.getSession());
        System.out.println("Task delete");
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
