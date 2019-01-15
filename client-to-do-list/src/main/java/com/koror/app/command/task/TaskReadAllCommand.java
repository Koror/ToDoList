package com.koror.app.command.task;

import com.koror.app.command.AbstractCommand;

public final class TaskReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
//        int index = 0;
//        String userId = bootstrap.getSession().getUserId();
//        final User user = bootstrap.getUserService().getById(userId);
//        for (Task task : bootstrap.getTaskService().getListTaskByUser(user)) {
//            final String creatorLogin = bootstrap.getUserService().getById(task.getCreator()).getLogin();
//            final String groupName = bootstrap.getGroupService().getById(task.getGroupId()).getName();
//            System.out.println(index + " [" + "Name:" + task.getName() + " Priority: "
//                    + task.getPriority() + " Complete:" + task.getComplete() + " Creator:"
//                    + creatorLogin + " Group:" + groupName + "]");
//            index++;
//        }
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
