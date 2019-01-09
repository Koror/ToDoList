package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Task;

import java.util.List;

public final class TaskCompleteCommand extends AbstractCommand {

    @Override
    public void execute() {
        final List<Task> taskList = bootstrap.getTaskService().getTaskList();
        System.out.println(taskList);
        System.out.println("Input index task");
        final Task task = taskList.get(bootstrap.nextInt());
        task.setComplete();
        bootstrap.getTaskService().completeTask(task);
    }

    @Override
    public String command() {
        return "CompleteTask";
    }

    @Override
    public String description() {
        return "Complete task";
    }

}
