package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandAddTask extends AbstractCommand {

    public CommandAddTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().addTask(bootstrap.gui.addTask());
    }

    @Override
    public String command() {
        return "AddTask";
    }

    @Override
    public void description() {
        System.out.println("Add new task");
    }
}
