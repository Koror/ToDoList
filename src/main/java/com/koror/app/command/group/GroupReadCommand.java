package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUserId(userId);
        System.out.println(groupList);
        final int groupIndex = bootstrap.nextInt();
        final Group group = bootstrap.getGroupService().getGroup(groupIndex);
        System.out.println(group.getName());
        for (Task task : bootstrap.getTaskService().getListTaskByUserId(userId)) {
            if (task.getGroupId().equals(group.getId()))
                System.out.println("  " + " [" + "Name:" + task.getText() + " Priority: "
                        + task.getPriority() + " Complete: " + task.getComplete() + "]");
        }
    }

    @Override
    public String command() {
        return "ReadGroup";
    }

    @Override
    public String description() {
        return "Read group";
    }

}
