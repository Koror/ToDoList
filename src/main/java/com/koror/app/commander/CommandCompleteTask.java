package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandCompleteTask extends AbstractCommand {

    public CommandCompleteTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().completeTask(bootstrap.gui.completeTask(bootstrap.taskService.getTaskRepository().getTaskList()));
    }

    @Override
    public String command() {
        return "CompleteTask";
    }

    @Override
    public void description() {
        System.out.println("Complete task");
    }
}
