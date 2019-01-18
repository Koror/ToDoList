package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Task;

import java.util.List;

public final class TaskToGroupCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList(bootstrap.getSession());
        final Task task = bootstrap.getTaskByList(taskList);
        final List<Group> groupList = bootstrap.getGroupService().getGroupList(bootstrap.getSession());
        final Group group = bootstrap.getGroupByList(groupList);
        Result result = bootstrap.getTaskService().taskToGroupTask(task.getId(), group.getId(), bootstrap.getSession());
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
