package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;

import java.io.IOException;
import java.util.List;

public class UserToTaskCommand extends AbstractCommand {
    @Override
    public void execute() {
        List<User> userList = bootstrap.getUserService().getUserList();
        System.out.println(userList);
        System.out.println("Input user index");
        String userId = userList.get(bootstrap.nextInt()).getId();

        User currentUser = bootstrap.getAuthorization().getUser();
        List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(currentUser);
        System.out.println(taskList);
        System.out.println("Input task index");
        String taskId = taskList.get(bootstrap.nextInt()).getId();

        AssigneeTask assigneeTask = new AssigneeTask(userId,taskId);
        bootstrap.getAssigneeTaskService().addAssignee(assigneeTask);
    }

    @Override
    public String command() {
        return "UserToTask";
    }

    @Override
    public String description() {
        return "Join user to task";
    }
}
