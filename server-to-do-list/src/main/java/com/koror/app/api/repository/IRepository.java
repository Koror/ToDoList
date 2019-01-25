package com.koror.app.api.repository;

import com.koror.app.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface IRepository<E extends AbstractEntity> {

    void add(E entity);

    void update(E entity);

}
