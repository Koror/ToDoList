package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Task;
import com.koror.app.enumerated.Priority;

public final class TaskAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input task name and priority{LOW MEDIUM HIGH}");
        final String name = bootstrap.nextLine();
        final String priority = bootstrap.nextLine();
        final Task task = new Task(name, Priority.getPriority(priority));
        final String userId = bootstrap.getAuthorization().getUser().getId();
        task.setCreator(userId);
        bootstrap.getTaskService().addTask(task);
        final AssigneeTask assigneeTask = new AssigneeTask(userId, task.getId());
        bootstrap.getAssigneeTaskService().addAssignee(assigneeTask);
        System.out.println("Task created");
    }

    @Override
    public String command() {
        return "AddTask";
    }

    @Override
    public String description() {
        return "Add new task";
    }

}
