package com.koror.app.command;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public final class GroupReadCommand extends AbstractCommand {

    @Override
    public void execute() {
        int indexGroup = 0;
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        for (final Group group : bootstrap.getGroupService().getGroupMap().values()) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (final Task task : taskList) {
                if (task.getGroupId() != null && task.getGroupId().equals(group.getId())) {
                    System.out.println("  " + " [" + "Name:" + task.getText() + " Priority: "
                            + task.getPriority() + " Complete: " + task.getComplete() + "]");
                }
            }
            indexGroup++;
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
