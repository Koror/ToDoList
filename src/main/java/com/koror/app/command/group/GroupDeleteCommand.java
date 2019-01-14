package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;

import java.util.List;

public final class GroupDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final User user = bootstrap.getAuthorization().getUser();
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUser(user);
        System.out.println(groupList);
        System.out.println("Input index group");
        final int inputIndex = bootstrap.nextInt();
        bootstrap.getAssigneeGroupService().deleteAssigneeByParam(user.getId(),groupList.get(inputIndex).getId());
        bootstrap.getGroupService().delete(groupList.get(inputIndex).getId());
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
