package com.koror.app.command;

import com.koror.app.entity.Task;

public final class TaskReadCommand extends AbstractCommand {

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
