package com.koror.app.command;

import com.koror.app.controller.Bootstrap;

public class CommandTaskClear extends AbstractCommand {

    public CommandTaskClear(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.getTaskRepository().clearTask();
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
