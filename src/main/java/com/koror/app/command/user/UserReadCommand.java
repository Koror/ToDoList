package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;

import java.util.List;

public class UserReadCommand extends AbstractCommand {
    @Override
    public void execute() {
        List<User> userList = bootstrap.getUserService().getList();
        System.out.println(userList);
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
