package com.koror.app.api.repository;

import com.koror.app.entity.Group;

import java.util.List;
import java.util.Map;

public interface IGroupRepository {

    void addGroup(final Group group);

    Group updateGroup(final Group group);

    Group deleteGroup(final String id);

    Map<String, Group> getGroupMap();

    List<Group> getGroupList();

    Group getGroup(final Integer index);

}
