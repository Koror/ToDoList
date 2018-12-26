package com.koror.app.command;

public final class TaskClearCommand extends AbstractCommand {

    @Override
    public void execute() {
        bootstrap.getTaskService().clearTask();
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
