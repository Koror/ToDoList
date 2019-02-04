package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.TaskDTO;
import com.koror.app.endpoint.UserDTO;

import java.util.List;

public class UserToTaskCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<UserDTO> userList = bootstrap.getUserEndpoint().getUserList(bootstrap.getSession());
        UserDTO user = bootstrap.getUserByList(userList);

        final List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        TaskDTO task = bootstrap.getTaskByList(taskList);

        Result result = bootstrap.getTaskEndpoint().linkToTaskUser(user, task, bootstrap.getSession());
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
