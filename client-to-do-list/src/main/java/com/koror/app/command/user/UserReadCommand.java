package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Session;
import com.koror.app.endpoint.User;

import java.util.List;

public class UserReadCommand extends AbstractCommand {
    @Override
    public void execute() {
        List<User> userList = bootstrap.getUserService().getUserList(bootstrap.getSession());
        for (User user : userList)
            System.out.println(user.getLogin());
    }

    @Override
    public String command() {
        return "ReadAllUser";
    }

    @Override
    public String description() {
        return "Read all users";
    }
}
