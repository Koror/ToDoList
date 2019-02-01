package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupDTO;
import com.koror.app.endpoint.Result;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        final GroupDTO group = new GroupDTO();
        group.setName(bootstrap.nextLine());
        Result result = bootstrap.getGroupEndpoint().addGroup(group, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Group created");
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
