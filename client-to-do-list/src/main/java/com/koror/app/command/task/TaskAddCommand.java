package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.TaskDTO;

public final class TaskAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input task name");
        final String name = bootstrap.nextLine();
        final TaskDTO task = new TaskDTO();
        task.setName(name);
        Result result = bootstrap.getTaskEndpoint().addTask(task, bootstrap.getSession());
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
