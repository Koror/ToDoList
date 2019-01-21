package com.koror.app.command.session;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.Session;

import java.util.List;

public class SessionDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<Session> list = bootstrap.getSessionService().getListSession(bootstrap.getSession());
        int indexSession = 0;
        for (Session session : list) {
            System.out.println(indexSession + " Id: " + session.getId() +
                    "user id: " + session.getUserId() +
                    "signature: " + session.getSignature() +
                    "ip: " + session.getIp());
            indexSession++;
        }
        System.out.println("Input index session");
        Session session = list.get(bootstrap.nextInt());
        Result result = bootstrap.getSessionService().deleteSession(session.getId(), bootstrap.getSession());
        System.out.println(result.getResult());
        System.out.println("Delete session complete");
    }

    @Override
    public String command() {
        return "DeleteSession";
    }

    @Override
    public String description() {
        return "Delete session by id";
    }
}
