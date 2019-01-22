package com.koror.app.api.repository;

import com.koror.app.entity.Group;

import java.util.List;

public interface IGroupRepository extends IRepository<Group>{

    void delete(String id);

    Group getById(String id);

    List<Group> getList();

}
