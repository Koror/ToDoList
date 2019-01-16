package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Task;
import com.koror.app.endpoint.User;

import java.util.List;

public final class TaskReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int index = 0;
        String userId = bootstrap.getSession().getUserId();
        List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        for (Task task : taskList) {
            User user = bootstrap.getUserService().getUserById(task.getCreator(),bootstrap.getSession());
            final String creatorLogin =user.getLogin();
            final String groupName ="null";// bootstrap.getGroupService().getById(task.getGroupId()).getName();
            System.out.println(index + " [" + "Name:" + task.getName() + " Priority: "
                    + task.getPriority() + " Complete:" + task.isComplete() + " Creator:"
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
