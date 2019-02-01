package com.koror.app.command.session;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Result;
import com.koror.app.endpoint.SessionDTO;

import java.util.List;

public class SessionDeleteCommand extends AbstractCommand {
    @Override
    public void execute() {
        final List<SessionDTO> list = bootstrap.getSessionEndpoint().getListSession(bootstrap.getSession());
        int indexSession = 0;
        for (SessionDTO session : list) {
            System.out.println(indexSession +
                    "signature: " + session.getSignature() +
                    "ip: " + session.getIp());
            indexSession++;
        }
        System.out.println("Input index session");
        SessionDTO session = list.get(bootstrap.nextInt());
        Result result = bootstrap.getSessionEndpoint().deleteSession(session, bootstrap.getSession());
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
