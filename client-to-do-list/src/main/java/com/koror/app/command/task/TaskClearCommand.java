package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskClearCommand extends AbstractCommand {

    @Override
    public void execute() {
//        String userId = bootstrap.getSession().getUserId();
//        final User user = bootstrap.getUserService().getById(userId);
//        List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
//        bootstrap.getTaskService().clearTask(taskList);
//        System.out.println("Complete task clear");
    }

    @Override
    public String command() {
        return "ClearTask";
    }

    @Override
    public String description() {
        return "Clear task";
    }

}
