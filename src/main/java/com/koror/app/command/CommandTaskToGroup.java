package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;
import java.util.Scanner;

public class CommandTaskToGroup extends AbstractCommand {

    public CommandTaskToGroup(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        try {
            final List<Task> taskList = bootstrap.getTaskRepository().getTaskList();
            final List<Group> groupList = bootstrap.getGroupRepository().getGroupList();
            System.out.println(taskList);
            System.out.println("Input index task");
            final Task task = taskList.get(Integer.parseInt(new Scanner(System.in).nextLine()));
            System.out.println(groupList);
            System.out.println("Input index group");
            final Group group = groupList.get(Integer.parseInt(new Scanner(System.in).nextLine()));
            task.setGroupId(group.getId());
            bootstrap.getTaskRepository().setGroupId(task);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
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
