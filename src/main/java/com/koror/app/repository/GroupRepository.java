package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository implements IGroupRepository {

    private final Map<String, Group> groupMap = new HashMap<>();

    public void addGroup(final Group group) {
        groupMap.put(group.getId(), group);
    }

    public Group updateGroup(final Group group) {
        return groupMap.put(group.getId(), group);
    }

    public Group deleteGroup(final String id) {
        return groupMap.remove(id);
    }

    public Map<String, Group> getGroupMap() {
        return groupMap;
    }

    public List<Group> getGroupList() {
        return new ArrayList<>(groupMap.values());
    }

    public Group getGroup(final Integer index) {
        return getGroupList().get(index);
    }

}
