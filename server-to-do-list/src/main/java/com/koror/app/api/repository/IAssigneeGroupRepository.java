package com.koror.app.api.repository;

import com.koror.app.entity.AssigneeGroup;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface IAssigneeGroupRepository extends EntityRepository<AssigneeGroup, String> {

    @Query("FROM AssigneeGroup a where a.user.id = ?1")
    AssigneeGroup getAssigneeByUserId(String userId);

    @Query("DELETE FROM AssigneeGroup a WHERE a.user.id = ?1, a.group.id = ?2")
    void deleteAssigneeByParam(String userId, String groupId);

}
