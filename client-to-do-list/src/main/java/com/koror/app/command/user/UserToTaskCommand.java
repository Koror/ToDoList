package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

public class UserToTaskCommand extends AbstractCommand {
    @Override
    public void execute() {
//        final List<User> userList = bootstrap.getUserService().getList();
//        System.out.println(userList);
//        System.out.println("Input user index");
//        final String userId = userList.get(bootstrap.nextInt()).getId();
//
//        final String currentUserId = bootstrap.getSession().getUserId();
//        final User currentUser = bootstrap.getUserService().getById(currentUserId);
//        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(currentUser);
//        System.out.println(taskList);
//        System.out.println("Input task index");
//        final String taskId = taskList.get(bootstrap.nextInt()).getId();
//
//        final AssigneeTask assigneeTask = new AssigneeTask(userId,taskId);
//        bootstrap.getAssigneeTaskService().add(assigneeTask);
    }

    @Override
    public String command() {
        return "UserToTask";
    }

    @Override
    public String description() {
        return "Join user to task";
    }
}
