package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public class CommandGroupRead extends AbstractCommand {

    public CommandGroupRead(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        int indexGroup = 0;
        final List<Task> taskList = bootstrap.getTaskRepository().getTaskList();
        for (Group group : bootstrap.getGroupRepository().getGroupMap().values()) {
            System.out.println(indexGroup + " [" + group.toString() + "]");
            for (Task task : taskList) {
                if (task.getGroupId() != null && task.getGroupId().equals(group.getId())) {
                    System.out.println("  " + " [" + "Name:" + task.getText() + " Priority: " + task.getPriority() + " Complete: " + task.getComplete() + " Id: " + task.getId() + " Group: " + task.getGroupId() + "]");
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
