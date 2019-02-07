package com.koror.app.api.repository;

import com.koror.app.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface IGroupRepository extends JpaRepository<Group, String> {

    @Query("FROM Group a where a.user.id = ?1")
    List<Group> getListGroupByUserId(final String userId);

}
