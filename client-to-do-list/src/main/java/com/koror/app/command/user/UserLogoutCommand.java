package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Session;

public class UserLogoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        final Session session = bootstrap.getSession();
        Session sessionCopy = new Session();
        sessionCopy.setId(session.getId());
        sessionCopy.setUserId(session.getUserId());
        sessionCopy.setSignature(session.getSignature());
        sessionCopy.setIp(session.getIp());
        bootstrap.deleteSession();
        System.out.println("Logout complete");
        final Result result = bootstrap.getUserService().logoutUser(sessionCopy);
        System.out.println(result.getResult());
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
