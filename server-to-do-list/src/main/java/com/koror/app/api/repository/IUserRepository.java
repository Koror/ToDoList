package com.koror.app.api.repository;

import com.koror.app.entity.User;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Repository
public interface IUserRepository extends EntityRepository<User, String> {

    @Query(value = "FROM User a where a.login = ?1")
    User findByLogin(String login);

}
