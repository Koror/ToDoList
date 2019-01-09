package com.koror.app.command.usercommand;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class LoginCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input login and password");
        final String login = bootstrap.nextLine();
        final String password = bootstrap.nextLine();
        bootstrap.getAuthorization().auth(login, password);
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
