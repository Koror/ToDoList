package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskToGroupCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getAuthorization().getUserId();
        final List<Task> taskList = bootstrap.getTaskService().getListTaskByUserId(userId);
        System.out.println(taskList);
        System.out.println("Input index task");
        final Task task = taskList.get(bootstrap.nextInt());
        final List<Group> groupList = bootstrap.getGroupService().getListGroupByUserId(userId);
        System.out.println(groupList);
        System.out.println("Input index group");
        final Group group = groupList.get(bootstrap.nextInt());
        task.setGroupId(group.getId());
        bootstrap.getTaskService().setGroupId(task);
        System.out.println("Link task to group");
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
