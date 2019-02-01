package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.TaskDTO;

import java.util.List;

public final class TaskReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int index = 0;
        List<TaskDTO> taskList = bootstrap.getTaskEndpoint().getTaskList(bootstrap.getSession());
        for (TaskDTO task : taskList) {
            final String groupName ="null";// bootstrap.getGroupEndpoint().getById(task.getGroupId()).getName();
            System.out.println(index + " [" + "Name:" + task.getName() + " Priority: "
                    + task.getPriority() + " Complete:" + task.isComplete() + " Group:" + groupName + "]");
            index++;
        }
    }

    @Override
    public String command() {
        return "ReadTaskAll";
    }

    @Override
    public String description() {
        return "Read all task";
    }

}
