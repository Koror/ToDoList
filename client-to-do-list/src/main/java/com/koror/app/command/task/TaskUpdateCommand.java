package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
//        String userId = bootstrap.getSession().getUserId();
//        final User user = bootstrap.getUserService().getById(userId);
//        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
//        System.out.println("index task, task name and priority{LOW MEDIUM HIGH}");
//        final Task task = taskList.get(bootstrap.nextInt());
//        task.setName(bootstrap.nextLine());
//        task.setPriority(Priority.valueOf(bootstrap.nextLine()));
//        bootstrap.getTaskService().update(task);
//        System.out.println("Task update");
    }

    @Override
    public String command() {
        return "UpdateTask";
    }

    @Override
    public String description() {
        return "Update task";
    }

}
