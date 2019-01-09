package com.koror.app.command.usercommand;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;

public class UserDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
        System.out.println(bootstrap.getUserService().getUserList());
        System.out.println("Input index");
        final int index = Integer.parseInt(bootstrap.nextLine());
        final User user = bootstrap.getUserService().getUserList().get(index);
        bootstrap.getUserService().deleteUser(user.getId());
    }

    @Override
    public String command() {
        return "DeleteUser";
    }

    @Override
    public String description() {
        return "Delete user";
    }
}
