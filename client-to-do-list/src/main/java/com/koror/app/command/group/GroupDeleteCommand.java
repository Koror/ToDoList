package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;
import com.koror.app.endpoint.Result;

import java.util.List;

public final class GroupDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Group> groupList = bootstrap.getGroupService().getGroupList(bootstrap.getSession());
        Group group = bootstrap.getGroupByList(groupList);
        Result result = bootstrap.getGroupService().deleteGroup(group.getId(), bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Group deleted");
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
