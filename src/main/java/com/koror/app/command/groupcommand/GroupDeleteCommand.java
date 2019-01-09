package com.koror.app.command.groupcommand;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;

import java.util.List;

public final class GroupDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Group> groupList = bootstrap.getGroupService().getGroupList();
        System.out.println(groupList);
        System.out.println("Input index group");
        bootstrap.getGroupService().deleteGroup(groupList.get(bootstrap.nextInt()).getId());
    }

    @Override
    public String command() {
        return "DeleteGroup";
    }

    @Override
    public String description() {
        return "Delete group";
    }

}
