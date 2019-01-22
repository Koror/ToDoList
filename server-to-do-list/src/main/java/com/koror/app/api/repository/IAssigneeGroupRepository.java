package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;

import java.util.List;

public interface IAssigneeGroupRepository extends IRepository<AssigneeGroup>{

    AssigneeGroup getAssigneeByUserId(String userId);

    void delete(String id);

    AssigneeGroup getById(String id);

    List<AssigneeGroup> getList();

}
