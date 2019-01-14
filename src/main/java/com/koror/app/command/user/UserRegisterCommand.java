package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.User;

public class UserRegisterCommand extends AbstractCommand {
    @Override
    public void execute() {
        System.out.println("Input login");
        final String login = bootstrap.nextLine();
        System.out.println("Input password");
        final String password = User.hashPassword(bootstrap.nextLine());
        final User user = new User(login, password);
        bootstrap.getUserService().add(user);
        bootstrap.getAuthorization().auth(user);
        System.out.println("User created and login");
    }

    @Override
    public String command() {
        return "RegisteredUser";
    }

    @Override
    public String description() {
        return "Registered new user";
    }

}
