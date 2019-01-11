package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;
import com.koror.app.error.UserNotExistsException;

import java.util.List;

public class UserLoginCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input login and password");
        final String login = bootstrap.nextLine();
        final String password = User.hashPassword(bootstrap.nextLine());
        final List<User> userList = bootstrap.getUserService().getUserList();
        User user=null;
        for (User userTemp : userList){
            if((login.equals(userTemp.getLogin())) && (password.equals(userTemp.getPassword())))
                user = userTemp;
        }
        if(user == null) throw new UserNotExistsException();
        bootstrap.getAuthorization().auth(user);
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
