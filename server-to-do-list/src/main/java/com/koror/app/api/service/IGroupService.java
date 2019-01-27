package com.koror.app.api.service;

import com.koror.app.entity.Group;
import com.koror.app.entity.User;

import java.util.List;

public interface IGroupService {

    void add(Group entity, User user);

    void delete(Group group, User user) ;

    Group getById(String id);

    List<Group> getList();

    List<Group> getListGroupByUserId(User user);

}
