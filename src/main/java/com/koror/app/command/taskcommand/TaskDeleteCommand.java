package com.koror.app.command.taskcommand;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        System.out.println(taskList);
        System.out.println("Input index task");
        bootstrap.getTaskService().deleteTask(taskList.get(bootstrap.nextInt()).getId());
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
