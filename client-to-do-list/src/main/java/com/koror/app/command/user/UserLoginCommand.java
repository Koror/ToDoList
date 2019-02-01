package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.SessionDTO;

public class UserLoginCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input login and password");
        final String login = bootstrap.nextLine();
        final String password = bootstrap.nextLine();
        final SessionDTO session = bootstrap.getUserEndpoint().loginUser(login, password, bootstrap.getIp());
        bootstrap.setSession(session);
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
