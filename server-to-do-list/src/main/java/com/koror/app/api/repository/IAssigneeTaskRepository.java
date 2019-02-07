package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssigneeTaskRepository extends JpaRepository<AssigneeTask, String> {

    @Query("FROM AssigneeTask a where a.user.id = ?1 and a.task.id = ?2")
    AssigneeTask getAssigneeByParam(String userId, String taskId);

}
