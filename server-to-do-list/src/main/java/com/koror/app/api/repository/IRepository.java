package com.koror.app.api.repository;

import com.koror.app.entity.AbstractEntity;

import java.util.List;

public interface IRepository<E extends AbstractEntity>{

    void add(E entity);

    void delete(String id);

    E getById(String id);

    List<E> getList();

    void update(final E entity);
}
