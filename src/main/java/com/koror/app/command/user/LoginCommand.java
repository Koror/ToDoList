package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;

import java.util.List;

public class LoginCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input login and password");
        final String login = bootstrap.nextLine();
        final String password = User.hashPassword(bootstrap.nextLine());
        final List<User> userList = bootstrap.getUserService().getUserList();
        String userId = null;
        for (User user : userList){
            if((login.equals(user.getLogin())) && (password.equals(user.getPassword())))
                userId = user.getId();
        }
        if(userId == null) throw new UserNotExistsException();
        bootstrap.getAuthorization().auth(login, password, userId);
        System.out.println("Login complete");
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
