package com.koror.app.api.repository;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public interface IGroupRepository extends IRepository<Group>{

    void delete(String id, EntityManager entityManager);

    Group getById(String id, EntityManager entityManager);

    List<Group> getList(EntityManager entityManager);

    List<Group> getListGroupByUserId(final String userId, EntityManager entityManager);

}
