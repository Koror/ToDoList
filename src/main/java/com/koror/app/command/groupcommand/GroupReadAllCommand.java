package com.koror.app.command.groupcommand;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public class GroupReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int indexGroup = 0;
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        for (final Group group : bootstrap.getGroupService().getGroupList()) {
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
        return "ReadGroupAll";
    }

    @Override
    public String description() {
        return "Read all group";
    }

}
