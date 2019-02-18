package com.koror.app.api.repository;

import com.koror.app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITaskRepository extends JpaRepository<Task, String> {

    @Query("FROM Task a where a.user.id = ?1")
    List<Task> getListTaskByUserId(final String userId);

    @Query("FROM Task a where a.group.id = ?1")
    List<Task> getListTaskByGroupId(final String groupId);

}
