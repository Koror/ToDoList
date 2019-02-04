package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupDTO;
import com.koror.app.endpoint.TaskDTO;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<GroupDTO> groupList = bootstrap.getGroupEndpoint().getGroupList(bootstrap.getSession());
        final GroupDTO group = bootstrap.getGroupByList(groupList);
        System.out.println(group.getName());
        final List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        for (TaskDTO task : taskList) {
            if (task.getGroup().getId().equals(group.getId()))
                System.out.println("  " + "Name:" + task.getName() + " Priority: "
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
