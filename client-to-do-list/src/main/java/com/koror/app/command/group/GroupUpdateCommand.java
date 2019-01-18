package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;
import com.koror.app.endpoint.Result;

import java.util.List;

public final class GroupUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Group> groupList = bootstrap.getGroupService().getGroupList(bootstrap.getSession());
        final Group group = bootstrap.getGroupByList(groupList);
        System.out.println("Input name");
        group.setName(bootstrap.nextLine());
        Result result = bootstrap.getGroupService().updateGroup(group, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Group update");
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
