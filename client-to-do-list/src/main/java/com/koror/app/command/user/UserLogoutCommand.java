package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class UserLogoutCommand extends AbstractCommand {
    @Override
    public void execute() {
//        bootstrap.logout();
//        System.out.println("Logout complete");
    }

    @Override
    public String command() {
        return "Logout";
    }

    @Override
    public String description() {
        return "Logout from app";
    }
}
