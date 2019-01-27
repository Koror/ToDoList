package com.koror.app.api.repository;

import com.koror.app.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

public interface ITaskRepository extends IRepository<Task>{

    void delete(String id, EntityManager entityManager);

    Task getById(String id, EntityManager entityManager);

    List<Task> getList(EntityManager entityManager);

    List<Task> getListTaskByUserId(String userId, EntityManager entityManager);

}
