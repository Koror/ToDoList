package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;

import java.util.List;

public final class GroupUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        System.out.println("Input index group and name");
        int indexGroup = 0;
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUserId(userId);
        for (final Group group : groupList) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
        }
        final Group group = groupList.get(bootstrap.nextInt());
        group.setName(bootstrap.nextLine());
        bootstrap.getGroupService().updateGroup(group);
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
