package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        System.out.println(taskList);
        System.out.println("Input index task");
        final List<AssigneeTask> listAssignee = bootstrap.getAssigneeTaskService().getAssigneeTaskList();
        final String userId = bootstrap.getAuthorization().getUserId();
        final int inputIndex = bootstrap.nextInt();
        for(AssigneeTask assigneeTask : listAssignee)
            if((assigneeTask.getUserId().equals(userId)) && (assigneeTask.getTaskId().equals(taskList.get(inputIndex))))
                bootstrap.getAssigneeTaskService().deleteAssignee(assigneeTask.getId());
        bootstrap.getTaskService().deleteTask(taskList.get(inputIndex).getId());
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
