package com.koror.app.api.repository;

import com.koror.app.entity.Group;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import java.util.List;

@Repository
public interface IGroupRepository extends EntityRepository<Group, String> {

    @Query("FROM Group a where a.user.id = ?1")
    List<Group> getListGroupByUserId(final String userId);

}
