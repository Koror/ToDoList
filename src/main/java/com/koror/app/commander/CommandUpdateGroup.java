package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandUpdateGroup extends AbstractCommand {

    public CommandUpdateGroup(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.groupService.getGroupRepository().updateGroup(bootstrap.gui.updateGroup(bootstrap.groupService.getGroupRepository().getGroupList()));
    }

    @Override
    public String command() {
        return "UpdateGroup";
    }

    @Override
    public void description() {
        System.out.println("Update group");
    }

}
