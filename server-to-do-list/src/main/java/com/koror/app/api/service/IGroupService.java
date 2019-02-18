package com.koror.app.api.service;

import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IGroupService {

    void add(@Nullable Group entity);

    void add(@Nullable Group entity, @Nullable User user);

    void delete(@Nullable Group group);

    void update(Group group);

    Group getById(@Nullable String id);

    List<Group> getList();

    List<Group> getListGroupByUser(@Nullable User user);

}
