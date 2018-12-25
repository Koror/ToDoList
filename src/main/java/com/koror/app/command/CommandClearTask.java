package com.koror.app.command;

import com.koror.app.controller.Bootstrap;

public class CommandClearTask extends AbstractCommand {

    public CommandClearTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().clearTask();
    }

    @Override
    public String command() {
        return "ClearTask";
    }

    @Override
    public void description() {
        System.out.println("Clear task");
    }

}
