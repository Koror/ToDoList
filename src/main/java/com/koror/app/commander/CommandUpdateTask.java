package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandUpdateTask extends AbstractCommand {

    public CommandUpdateTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().updateTask(bootstrap.gui.updateTask(bootstrap.taskService.getTaskRepository().getTaskList()));
    }

    @Override
    public String command() {
        return "UpdateTask";
    }

    @Override
    public void description() {
        System.out.println("Update task");
    }
}
