package com.koror.app.command.session;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.SessionDTO;

import java.io.IOException;
import java.util.List;

public class SessionReadAllCommand extends AbstractCommand {

    @Override
    public void execute() throws IOException, ClassNotFoundException {
        List<SessionDTO> list = bootstrap.getSessionEndpoint().getListSession(bootstrap.getSession());
        for (SessionDTO session : list) {
            System.out.println("Signature: " + session.getSignature() +
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
