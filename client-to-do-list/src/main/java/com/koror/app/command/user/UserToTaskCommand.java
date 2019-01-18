package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Task;
import com.koror.app.endpoint.User;

import java.util.List;

public class UserToTaskCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<User> userList = bootstrap.getUserService().getUserList(bootstrap.getSession());
        User user = bootstrap.getUserByList(userList);
        final String userId = user.getId();

        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        Task task = bootstrap.getTaskByList(taskList);
        final String taskId = task.getId();

        Result result = bootstrap.getUserService().linkToTaskUser(userId, taskId, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Link complete");
    }

    @Override
    public String command() {
        return "UserToTask";
    }

    @Override
    public String description() {
        return "Link user to task";
    }
}
