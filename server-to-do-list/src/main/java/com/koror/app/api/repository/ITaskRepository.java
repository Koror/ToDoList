package com.koror.app.api.repository;

import com.koror.app.entity.Task;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends EntityRepository<Task, String> {

    @Query("FROM Task a where a.user.id = ?1")
    List<Task> getListTaskByUserId(final String userId);

}
