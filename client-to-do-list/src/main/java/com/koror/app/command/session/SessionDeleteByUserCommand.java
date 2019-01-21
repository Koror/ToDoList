package com.koror.app.command.session;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;

public class SessionDeleteByUserCommand extends AbstractCommand {

    @Override
    public void execute() {
        final String userId = bootstrap.getSession().getUserId();
        Result result = bootstrap.getSessionService().deleteByUserSession(userId, bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Delete sessions complete");
    }

    @Override
    public String command() {
        return "DeleteSessionByUser";
    }

    @Override
    public String description() {
        return "Delete all session by user except this";
    }
}
