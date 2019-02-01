package com.koror.app.command.group;

import com.koror.app.command.AbstractCommand;
import com.koror.app.endpoint.GroupDTO;

import java.util.List;

public class GroupReadAllCommand extends AbstractCommand {

    @Override
    public void execute() {
        int indexGroup = 0;
        final List<GroupDTO> groupList = bootstrap.getGroupEndpoint().getGroupList(bootstrap.getSession());
        for (GroupDTO group : groupList) {
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
