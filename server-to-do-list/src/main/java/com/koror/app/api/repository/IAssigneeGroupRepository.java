package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IAssigneeGroupRepository extends JpaRepository<AssigneeGroup, String> {

    @Query("FROM AssigneeGroup a where a.user.id = ?1 and a.group.id = ?2")
    AssigneeGroup getAssigneeByParam(String userId, String groupId);

}
