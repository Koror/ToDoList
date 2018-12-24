package com.koror.app.commander;

import com.koror.app.controller.Bootstrap;

public class CommandAddGroupToTask extends AbstractCommand {

    public CommandAddGroupToTask(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        bootstrap.taskService.getTaskRepository().setGroupId(bootstrap.gui.AddGroupToTask(bootstrap.taskService.getTaskRepository().getTaskList(), bootstrap.groupService.getGroupRepository().getGroupList()));
    }

    @Override
    public String command() {
        return "AddGroupToTask";
    }

    @Override
    public void description() {
        System.out.println("Add group to existing task");
    }
}
