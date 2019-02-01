package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;

public final class TaskClearCommand extends AbstractCommand {

    @Override
    public void execute() {
        Result result = bootstrap.getTaskEndpoint().clearTask(bootstrap.getSession());
        System.out.println(result.getResult());
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
