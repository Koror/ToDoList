package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;
import com.koror.app.endpoint.Task;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Group> groupList = bootstrap.getGroupService().getGroupList(bootstrap.getSession());
        final Group group = bootstrap.getGroupByList(groupList);
        System.out.println(group.getName());
        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        for (Task task : taskList) {
            if (task.getGroupId().equals(group.getId()))
                System.out.println("  "  + "Name:" + task.getName() + " Priority: "
                        + task.getPriority() + " Complete: " + task.isComplete() + "]");
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
