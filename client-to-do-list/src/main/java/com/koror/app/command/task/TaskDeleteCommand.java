package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.TaskDTO;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        TaskDTO task = bootstrap.getTaskByList(taskList);
        Result result = bootstrap.getTaskEndpoint().deleteTask(task, bootstrap.getSession());
        System.out.println(result.getResult());
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
