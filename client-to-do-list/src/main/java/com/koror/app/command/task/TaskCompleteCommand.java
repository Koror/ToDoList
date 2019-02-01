package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.TaskDTO;

import java.util.List;

public final class TaskCompleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        final TaskDTO task = bootstrap.getTaskByList(taskList);
        Result result = bootstrap.getTaskEndpoint().completeTask(task, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Task complete");
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
