package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUserId(userId);
        System.out.println(taskList);
        System.out.println("Input index task");
        final int inputIndex = bootstrap.nextInt();
        bootstrap.getAssigneeTaskService().deleteAssigneeByParam(userId, taskList.get(inputIndex).getId());
        bootstrap.getTaskService().deleteTask(taskList.get(inputIndex).getId());
        System.out.println("Task delete");
    }

    @Override
    public String command() {
        return "DeleteTask";
    }

    @Override
    public String description() {
        return "Delete task";
    }

}
