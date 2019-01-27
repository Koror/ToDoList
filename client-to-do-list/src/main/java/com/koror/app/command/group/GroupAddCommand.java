package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.User;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        final Group group = new Group();
        group.setName(bootstrap.nextLine());
        final User user = bootstrap.getSession().getUser();
        group.setCreator(user.getId());
        group.setUser(user);
        Result result = bootstrap.getGroupService().addGroup(group, bootstrap.getSession());
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
