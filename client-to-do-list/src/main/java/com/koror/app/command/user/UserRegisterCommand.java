package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

public class UserRegisterCommand extends AbstractCommand {
    @Override
    public void execute() {
//        System.out.println("Input login");
//        final String login = bootstrap.nextLine();
//        System.out.println("Input password");
//        final String password = User.hashPassword(bootstrap.nextLine());
//        final User user = new User(login, password);
//        bootstrap.getUserService().add(user);
//        bootstrap.setSession(new Session());
//        bootstrap.getSession().auth(user.getId());
//        System.out.println("User created and login");
    }

    @Override
    public String command() {
        return "RegisterUser";
    }

    @Override
    public String description() {
        return "Registered new user";
    }

}
