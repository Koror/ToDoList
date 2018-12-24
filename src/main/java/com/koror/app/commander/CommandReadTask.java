package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandReadTask extends AbstractCommand {

    public CommandReadTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.gui.readAllTask();
    }

    @Override
    public String command() {
        return "ReadTask";
    }

    @Override
    public void description() {
        System.out.println("Read task");
    }
}
