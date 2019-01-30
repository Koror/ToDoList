package com.koror.app.api.service;

import com.koror.app.entity.Group;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IGroupService {

    void add(@Nullable Group entity,@Nullable  User user);

    void delete(@Nullable Group group, @Nullable  User user) ;

    Group getById(@Nullable String id);

    List<Group> getList();

    List<Group> getListGroupByUserId(@Nullable User user);

    void add(@Nullable final Group entity);
}
