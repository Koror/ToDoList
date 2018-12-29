package com.koror.app.command;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Group> groupList = bootstrap.getGroupService().getGroupList();
        System.out.println(groupList);
        final int groupIndex = bootstrap.nextInt();
        System.out.println(bootstrap.getGroupService().getGroup(groupIndex).getName());
        for (final Task task : bootstrap.getTaskService().getTaskList()) {
            if (task.getGroupId() != null && task.getGroupId().equals(groupList.get(groupIndex).getId())) {
                System.out.println("  " + " [" + "Name:" + task.getText() + " Priority: "
                        + task.getPriority() + " Complete: " + task.getComplete() + "]");
            }
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
