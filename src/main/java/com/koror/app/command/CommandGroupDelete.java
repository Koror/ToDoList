package com.koror.app.command;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Group;

import java.util.List;
import java.util.Scanner;

public class CommandGroupDelete extends AbstractCommand {

    public CommandGroupDelete(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public void execute() {
        try {
            final List<Group> groupList = bootstrap.getGroupRepository().getGroupList();
            System.out.println(groupList);
            System.out.println("Input index group");
            bootstrap.getGroupRepository().deleteGroup(groupList.get(Integer.parseInt(new Scanner(System.in).nextLine())).getId());
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public String command() {
        return "DeleteGroup";
    }

    @Override
    public String description() {
        return "Delete group";
    }

}
