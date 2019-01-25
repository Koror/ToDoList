package com.koror.app.command.session;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Session;

import java.io.IOException;
import java.util.List;

public class SessionReadAllCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        List<Session> list = bootstrap.getSessionService().getListSession(bootstrap.getSession());
        for(Session session : list){
            System.out.println("Id: " + session.getId() +
                    " user: " + session.getUser().getId() +
                    " signature: " + session.getSignature() +
                    " ip: " + session.getIp());
        }
    }

    @Override
    public String command() {
        return "ReadSessionAll";
    }

    @Override
    public String description() {
        return "Read all session";
    }
}
