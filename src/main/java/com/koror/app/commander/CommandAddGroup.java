package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandAddGroup extends AbstractCommand {

    public CommandAddGroup(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.groupService.getGroupRepository().addGroup(bootstrap.gui.addGroup());
    }

    @Override
    public String command() {
        return "AddGroup";
    }

    @Override
    public void description() {
        System.out.println("Add new group");
    }

}
