package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.SessionDTO;
import com.koror.app.endpoint.UserDTO;

public class UserRegisterCommand extends AbstractCommand {
    @Override
    public void execute() {
        System.out.println("Input login");
        final String login = bootstrap.nextLine();
        System.out.println("Input password");
        final String password = bootstrap.nextLine();
        final SessionDTO session = bootstrap.getUserEndpoint().registerUser(login, password, bootstrap.getIp());
        bootstrap.setSession(session);
        System.out.println("User created and login");
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
