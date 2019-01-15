package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskAddCommand extends AbstractCommand {

    @Override
    public void execute() {
//        System.out.println("Input task name");
//        final String name = bootstrap.nextLine();
//        final Task task = new Task();
//        task.setName(name);
//        final String userId = bootstrap.getSession().getUserId();
//        task.setCreator(userId);
//        bootstrap.getTaskService().add(task);
//        final AssigneeTask assigneeTask = new AssigneeTask(userId, task.getId());
//        bootstrap.getAssigneeTaskService().add(assigneeTask);
//        System.out.println("Task created");
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
