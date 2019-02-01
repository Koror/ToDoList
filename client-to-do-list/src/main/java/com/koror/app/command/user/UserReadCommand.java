package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.UserDTO;

import java.util.List;

public class UserReadCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<UserDTO> userList = bootstrap.getUserEndpoint().getUserList(bootstrap.getSession());
        for (UserDTO user : userList)
            System.out.println(user.getLogin());
    }

    @Override
    public String command() {
        return "ReadUserAll";
    }

    @Override
    public String description() {
        return "Read all users";
    }
}
