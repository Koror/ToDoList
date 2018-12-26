package com.koror.app.command;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskToGroupCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        System.out.println(taskList);
        System.out.println("Input index task");
        final Task task = taskList.get(bootstrap.nextInt());
        final List<Group> groupList = bootstrap.getGroupService().getGroupList();
        System.out.println(groupList);
        System.out.println("Input index group");
        final Group group = groupList.get(bootstrap.nextInt());
        task.setGroupId(group.getId());
        bootstrap.getTaskService().setGroupId(task);
    }

    @Override
    public String command() {
        return "AddGroupToTask";
    }

    @Override
    public String description() {
        return "Add group to existing task";
    }

}
