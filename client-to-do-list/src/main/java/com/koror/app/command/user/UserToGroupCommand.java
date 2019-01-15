package com.koror.app.command.user;

import com.koror.app.command.AbstractCommand;

import java.io.IOException;

public class UserToGroupCommand extends AbstractCommand {
    @Override
    public void execute(){

    }

    @Override
    public String command() {
        return "UserToGroup";
    }

    @Override
    public String description() {
        return "Link user to all task of group";
    }
}
