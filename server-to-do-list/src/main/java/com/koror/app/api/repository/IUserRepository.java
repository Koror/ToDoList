package com.koror.app.api.repository;

import com.koror.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepository extends JpaRepository<User, String> {

    @Query(value = "FROM User a where a.login = ?1")
    User findByLogin(String login);

}
