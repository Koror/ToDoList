package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;

import java.util.List;

public class LoginCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input login and password");
        final String login = bootstrap.nextLine();
        final String password = bootstrap.nextLine();
        final List<User> userList = bootstrap.getUserService().getUserList();
        String userId = null;
        for (User user : userList){
            if((login.equals(user.getLogin())) && (password.equals(user.getPassword())))
                userId = user.getId();
        }
        bootstrap.getAuthorization().auth(login, password, userId);
    }

    @Override
    public String command() {
        return "Login";
    }

    @Override
    public String description() {
        return "Login in app";
    }
}
