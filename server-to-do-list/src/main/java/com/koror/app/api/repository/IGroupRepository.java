package com.koror.app.api.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGroupRepository extends IRepository<Group>{

    void delete(String id);

    Group getById(String id);

    List<Group> getList();

    List<Group> getListGroupByUserId(final String userId);

}
