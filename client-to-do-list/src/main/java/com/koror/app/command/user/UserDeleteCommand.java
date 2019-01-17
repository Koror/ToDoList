package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.User;

import java.util.List;

public class UserDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<User> userList = bootstrap.getUserService().getUserList(bootstrap.getSession());
        for (User user : userList)
            System.out.println(user.getLogin());
        System.out.println("Input index");
        final int indexUser = bootstrap.nextInt();
        final User user = userList.get(indexUser);
        bootstrap.getUserService().deleteUser(user.getId(), bootstrap.getSession());
        System.out.println("User delete");
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
