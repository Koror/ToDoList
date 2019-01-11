package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import java.util.List;

public final class TaskClearCommand extends AbstractCommand {

    @Override
    public void execute() {
        final User user = bootstrap.getAuthorization().getUser();
        List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
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
