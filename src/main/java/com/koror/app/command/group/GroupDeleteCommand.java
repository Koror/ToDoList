package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;

import java.util.List;

public final class GroupDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUserId(userId);
        System.out.println(groupList);
        System.out.println("Input index group");
        final int inputIndex = bootstrap.nextInt();
        bootstrap.getAssigneeGroupService().deleteAssigneeByParam(userId,groupList.get(inputIndex).getId());
        bootstrap.getGroupService().deleteGroup(groupList.get(inputIndex).getId());
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
