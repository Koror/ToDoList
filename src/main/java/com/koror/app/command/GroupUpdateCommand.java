package com.koror.app.command;

import com.koror.app.entity.Group;

import java.util.Scanner;

public final class GroupUpdateCommand extends AbstractCommand {

    @Override
    public void execute() {
        try {
            System.out.println("Input index group and name");
            final Group group = bootstrap.getGroupRepository().getGroupList().get(Integer.parseInt(new Scanner(System.in).nextLine()));
            group.setName(new Scanner(System.in).nextLine());
            bootstrap.getGroupRepository().updateGroup(group);
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong index");
        }
    }

    @Override
    public String command() {
        return "UpdateGroup";
    }

    @Override
    public String description() {
        return "Update group";
    }

}
