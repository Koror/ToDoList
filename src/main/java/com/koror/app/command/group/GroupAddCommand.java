package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        final Group group = new Group(bootstrap.nextLine());
        bootstrap.getGroupService().addGroup(group);
        final String userId = bootstrap.getAuthorization().getUserId();
        final AssigneeGroup assigneeGroup = new AssigneeGroup(userId, group.getId());
        bootstrap.getAssigneeGroupService().addAssignee(assigneeGroup);
        System.out.println("Group created");
    }

    @Override
    public String command() {
        return "AddGroup";
    }

    @Override
    public String description() {
        return "Add new group";
    }

}
