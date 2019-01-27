package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Task;
import com.koror.app.endpoint.User;

public final class TaskAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input task name");
        final String name = bootstrap.nextLine();
        final Task task = new Task();
        task.setName(name);
        final User user = bootstrap.getSession().getUser();
        task.setCreator(user.getId());
        task.setUser(user);
        Result result = bootstrap.getTaskService().addTask(task, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Task created");
    }

    @Override
    public String command() {
        return "AddTask";
    }

    @Override
    public String description() {
        return "Add new task";
    }

}
