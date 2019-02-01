package com.koror.app.api.repository;

import com.koror.app.entity.Session;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ISessionRepository extends EntityRepository<Session, String> {

    @Query(value = "FROM Session a where a.signature = ?1")
    Session findBySignature(String signature);
}
