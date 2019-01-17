package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Task;
import com.koror.app.endpoint.User;

import java.util.List;

public final class TaskCompleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        final Task task = bootstrap.getTaskByList(taskList);
        Result result = bootstrap.getTaskService().completeTask(task.getId(), bootstrap.getSession());
        System.out.println(result);
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
