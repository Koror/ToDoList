package com.koror.app.repository;

import com.koror.app.entity.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository {

    private final Map<String, Group> groupMap = new HashMap<>();

    public void addGroup(Group group) {
        groupMap.put(group.getId(), group);
    }

    public void updateGroup(Group group) {
        groupMap.put(group.getId(), group);
    }

    public void deleteGroup(String id) {
        try {
            groupMap.remove(id);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("Wrong index");
        }
    }

    public Map<String, Group> getGroupMap() {
        return groupMap;
    }

    public List<Group> getGroupList() {
        return new ArrayList<>(groupMap.values());
    }

}
