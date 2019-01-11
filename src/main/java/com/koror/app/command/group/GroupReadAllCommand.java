package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import java.util.List;

public class GroupReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int indexGroup = 0;
        final User user = bootstrap.getAuthorization().getUser();
        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
        for (Group group : bootstrap.getGroupService().getListGroupByUser(user)) {
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
