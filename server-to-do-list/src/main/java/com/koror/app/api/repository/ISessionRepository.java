package com.koror.app.api.repository;

import com.koror.app.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISessionRepository extends JpaRepository<Session, String> {

    @Query(value = "FROM Session a where a.signature = ?1")
    Session findBySignature(String signature);
}
