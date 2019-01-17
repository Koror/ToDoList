package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Task;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        Task task = bootstrap.getTaskByList(taskList);
        bootstrap.getTaskService().deleteTask(task.getId(), bootstrap.getSession());
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
