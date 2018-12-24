package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandReadGroup extends AbstractCommand {

    public CommandReadGroup(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.gui.readAllGroup();
    }

    @Override
    public String command() {
        return "ReadGroup";
    }

    @Override
    public void description() {
        System.out.println("Read group");
    }
}
