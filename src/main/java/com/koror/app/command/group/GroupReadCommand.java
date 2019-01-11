package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        final User user = bootstrap.getAuthorization().getUser();
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUser(user);
        System.out.println(groupList);
        System.out.println("Input index");
        final int groupIndex = bootstrap.nextInt();
        final Group group = bootstrap.getGroupService().getGroup(groupIndex);
        System.out.println(group.getName());
        for (Task task : bootstrap.getTaskService().getListTaskByUser(user)) {
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
