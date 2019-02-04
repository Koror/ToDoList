package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface IAssigneeTaskRepository extends EntityRepository<AssigneeTask, String> {

    @Query("FROM AssigneeTask a where a.user.id = ?1 and a.task.id = ?2")
    AssigneeTask getAssigneeByParam(String userId, String taskId);

}
