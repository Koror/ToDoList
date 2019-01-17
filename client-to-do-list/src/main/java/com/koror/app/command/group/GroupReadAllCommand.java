package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.Group;

import java.util.List;

public class GroupReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int indexGroup = 0;
        String userId = bootstrap.getSession().getUserId();
        final List<Group> groupList = bootstrap.getGroupService().getGroupList(bootstrap.getSession());
        for (Group group : groupList) {
            System.out.println(indexGroup + ": " + group.getName() + " Creator " + group.getCreator());
            indexGroup++;
        }
    }

    @Override
    public String command() {
        return "ReadGroupAll";
    }

    @Override
    public String description() {
        return "Read all group";
    }

}
