package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskToGroupCommand extends AbstractCommand {

    @Override
    public void execute() {
//        String userId = bootstrap.getSession().getUserId();
//        final User user = bootstrap.getUserService().getById(userId);
//        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
//        System.out.println(taskList);
//        System.out.println("Input index task");
//        final Task task = taskList.get(bootstrap.nextInt());
//        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUser(user);
//        System.out.println(groupList);
//        System.out.println("Input index group");
//        final Group group = groupList.get(bootstrap.nextInt());
//        task.setGroupId(group.getId());
//        bootstrap.getTaskService().setGroupId(task);
//        System.out.println("Link complete");
    }

    @Override
    public String command() {
        return "AddTaskToGroup";
    }

    @Override
    public String description() {
        return "Add task to existing group";
    }

}
