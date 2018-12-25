package com.koror.app.command;

import com.koror.app.controller.Bootstrap;

public class CommandDeleteTask extends AbstractCommand {

    public CommandDeleteTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().deleteTask(bootstrap.gui.deleteTask(bootstrap.taskService.getTaskRepository().getTaskList()));
    }

    @Override
    public String command() {
        return "DeleteTask";
    }

    @Override
    public void description() {
        System.out.println("Delete task");
    }

}
