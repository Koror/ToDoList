package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskDeleteCommand extends AbstractCommand {

    @Override
    public void execute() {
//        String userId = bootstrap.getSession().getUserId();
//        final User user = bootstrap.getUserService().getById(userId);
//        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
//        System.out.println(taskList);
//        System.out.println("Input index task");
//        final int inputIndex = bootstrap.nextInt();
//        bootstrap.getAssigneeTaskService().deleteAssigneeByParam(user.getId(), taskList.get(inputIndex).getId());
//        bootstrap.getTaskService().delete(taskList.get(inputIndex).getId());
//        System.out.println("Task delete");
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
