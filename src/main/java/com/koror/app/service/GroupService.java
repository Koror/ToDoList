package com.koror.app.service;

import com.koror.app.entity.Group;
import com.koror.app.manager.GroupManager;

import java.util.List;

import static com.koror.app.service.ToDoListService.gui;

public class GroupService {

    private final GroupManager groupManager = new GroupManager();

    public void addGroup() {
        gui.addGroup();
        groupManager.addGroup(gui.getNameGroup());
    }

    public void readAllGroup() {
        gui.readAllGroup(getGroupList());
    }

    public void readGroup() {
        gui.readGroup(getGroupList());
    }

    public void updateGroup() {
        gui.updateGroup();
        groupManager.updateGroup(gui.getIndexGroup(), gui.getNameGroup());
    }

    public void deleteGroup() {
        gui.deleteGroup();
        groupManager.deleteGroup(gui.getIndexGroup());
    }

    public List<Group> getGroupList() {
        return groupManager.getGroupList();
    }
}
