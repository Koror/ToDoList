package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupDTO;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.TaskDTO;

import java.util.List;

public final class TaskToGroupCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        final TaskDTO task = bootstrap.getTaskByList(taskList);
        final List<GroupDTO> groupList = bootstrap.getGroupEndpoint().getGroupList(bootstrap.getSession());
        final GroupDTO group = bootstrap.getGroupByList(groupList);
        Result result = bootstrap.getTaskEndpoint().taskToGroupTask(task, group, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Link complete");
    }

    @Override
    public String command() {
        return "TaskToGroup";
    }

    @Override
    public String description() {
        return "Add task to existing group";
    }

}
