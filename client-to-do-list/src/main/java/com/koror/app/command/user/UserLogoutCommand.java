package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.SessionDTO;

public class UserLogoutCommand extends AbstractCommand {
    @Override
    public void execute() {
        //delete session on client first, because on server may be exception
        final SessionDTO session = bootstrap.getSession();
        SessionDTO sessionCopy = new SessionDTO();
        sessionCopy.setSignature(session.getSignature());
        sessionCopy.setIp(session.getIp());
        bootstrap.deleteSession();
        System.out.println("Logout complete");
        final Result result = bootstrap.getUserEndpoint().logoutUser(sessionCopy);
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
