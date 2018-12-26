package com.koror.app.command;

import com.koror.app.entity.Group;

import java.util.Scanner;

public final class GroupAddCommand extends AbstractCommand {

    @Override
    public void execute() {
        System.out.println("Input name group");
        bootstrap.getGroupRepository().addGroup(new Group(new Scanner(System.in).nextLine()));
    }

    @Override
    public String command() {
        return "AddGroup";
    }

    @Override
    public String description() {
        return "Add new group";
    }

}
