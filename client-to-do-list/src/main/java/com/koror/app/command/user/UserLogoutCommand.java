package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Session;

public class UserLogoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        final Session session = bootstrap.getSession();
        final Result result = bootstrap.getUserService().logoutUser(session);
        bootstrap.setSession(null);
        System.out.println(result.getResult());
        System.out.println("Logout complete");
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
