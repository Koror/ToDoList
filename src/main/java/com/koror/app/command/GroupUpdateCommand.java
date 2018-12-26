package com.koror.app.command;

import com.koror.app.entity.Group;

public final class GroupUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input index group and name");
        final Group group = bootstrap.getGroupService().getGroupList().get(bootstrap.nextInt());
        group.setName(bootstrap.nextLine());
        bootstrap.getGroupService().updateGroup(group);
    }

    @Override
    public String command() {
        return "UpdateGroup";
    }

    @Override
    public String description() {
        return "Update group";
    }

}
