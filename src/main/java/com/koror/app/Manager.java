package com.koror.app;

import java.util.ArrayList;

public class Manager {
    ArrayList<Group> groupList = new ArrayList<>();
    public void addGroup(String name)
    {
        Group group = new Group(name);
        groupList.add(group);
    }

}
