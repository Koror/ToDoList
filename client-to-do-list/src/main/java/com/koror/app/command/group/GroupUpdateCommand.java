package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupDTO;
import com.koror.app.endpoint.Result;

import java.util.List;

public final class GroupUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<GroupDTO> groupList = bootstrap.getGroupEndpoint().getGroupList(bootstrap.getSession());
        final GroupDTO group = bootstrap.getGroupByList(groupList);
        System.out.println("Input name");
        group.setName(bootstrap.nextLine());
        Result result = bootstrap.getGroupEndpoint().updateGroup(group, bootstrap.getSession());
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
