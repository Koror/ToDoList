package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

public final class TaskReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int index = 0;
        final User user = bootstrap.getAuthorization().getUser();
        for (Task task : bootstrap.getTaskService().getListTaskByUser(user)) {
            final String creatorLogin = bootstrap.getUserService().getById(task.getCreator()).getLogin();
            final String groupName = bootstrap.getGroupService().getGroupById(task.getGroupId()).getName();
            System.out.println(index + " [" + "Name:" + task.getText() + " Priority: "
                    + task.getPriority() + " Complete:" + task.getComplete() + " Creator:"
                    + creatorLogin + " Group:" + groupName + "]");
            index++;
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
