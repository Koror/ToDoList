package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int index = 0;
        final String userId = bootstrap.getAuthorization().getUserId();
        for (AssigneeTask assigneeTask : bootstrap.getAssigneeTaskService().getAssigneeTaskList()) {
            if(assigneeTask.getUserId().equals(userId)) {
                final Task task = bootstrap.getTaskService().getTaskById(assigneeTask.getTaskId());
                System.out.println(index + " [" + "Name:" + task.getText() + " Priority: "
                        + task.getPriority() + " Complete: " + task.getComplete() + " Id: "
                        + task.getId() + " Group: " + task.getGroupId() + "]");
                index++;
            }
        }
    }

    @Override
    public String command() {
        return "ReadTaskAll";
    }

    @Override
    public String description() {
        return "Read all task";
    }

}
