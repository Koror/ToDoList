package com.koror.app.command.groupcommand;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Group;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        final Group group = new Group(bootstrap.nextLine());
        bootstrap.getGroupService().addGroup(group);
    }

    @Override
    public String command() {
        return "AddGroup";
    }

    @Override
    public String description() {
        return "Add new group";
    }

}
