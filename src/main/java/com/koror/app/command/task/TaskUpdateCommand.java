package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;
import com.koror.app.enumerated.Priority;

import java.util.List;

public final class TaskUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        System.out.println("index task, task name and priority{LOW MEDIUM HIGH}");
        final Task task = taskList.get(bootstrap.nextInt());
        task.setText(bootstrap.nextLine());
        task.setPriority(Priority.valueOf(bootstrap.nextLine()));
        bootstrap.getTaskService().updateTask(task);
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
