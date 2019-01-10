package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskClearCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        List<Task> taskList = bootstrap.getTaskService().getListTaskByUserId(userId);
        bootstrap.getTaskService().clearTask(taskList);
        System.out.println("Complete task clear");
    }

    @Override
    public String command() {
        return "ClearTask";
    }

    @Override
    public String description() {
        return "Clear task";
    }

}
