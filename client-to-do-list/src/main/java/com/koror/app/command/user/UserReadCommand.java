package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

public class UserReadCommand extends AbstractCommand {
    @Override
    public void execute() {
//        List<User> userList = bootstrap.getUserService().getList();
//        System.out.println(userList);
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
