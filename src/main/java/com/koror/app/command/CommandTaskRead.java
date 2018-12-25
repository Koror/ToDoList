package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Task;

public class CommandTaskRead extends AbstractCommand {

    public CommandTaskRead(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        int index = 0;
        for (Task task : bootstrap.getTaskRepository().getTaskMap().values()) {
            System.out.println(index + " [" + "Name:" + task.getText() + " Priority: " + task.getPriority() + " Complete: " + task.getComplete() + " Id: " + task.getId() + " Group: " + task.getGroupId() + "]");
            index++;
        }
    }

    @Override
    public String command() {
        return "ReadTask";
    }

    @Override
    public String description() {
        return "Read task";
    }

}
