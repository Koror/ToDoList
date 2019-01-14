package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeGroup;
import com.koror.app.entity.Group;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        final Group group = new Group(bootstrap.nextLine());
        final String userId = bootstrap.getAuthorization().getUser().getId();
        group.setCreator(userId);
        bootstrap.getGroupService().add(group);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(userId, group.getId());
        bootstrap.getAssigneeGroupService().add(assigneeGroup);
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
