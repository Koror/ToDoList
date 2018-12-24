package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandDeleteGroup extends AbstractCommand {

    public CommandDeleteGroup(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.groupService.getGroupRepository().deleteGroup(bootstrap.gui.deleteGroup(bootstrap.groupService.getGroupRepository().getGroupList()));
    }

    @Override
    public String command() {
        return "DeleteGroup";
    }

    @Override
    public void description() {
        System.out.println("Delete group");
    }
}
