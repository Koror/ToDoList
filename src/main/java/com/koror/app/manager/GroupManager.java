package com.koror.app.manager;

import com.koror.app.entity.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupManager {

    private final List<Group> groupList = new ArrayList<>();

    public List<Group> getGroupList() {
        return groupList;
    }

    public void addGroup(String name) {
        groupList.add(new Group(name));
    }

    public void updateGroup(int indexGroup, String name) {
        groupList.get(indexGroup).changeName(name);
    }

    public void deleteGroup(int indexGroup) {
        try {
            groupList.remove(indexGroup);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

}
