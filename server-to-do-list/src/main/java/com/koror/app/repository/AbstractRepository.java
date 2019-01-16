package com.koror.app.repository;

import com.koror.app.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository<E extends AbstractEntity> {

    protected final Map<String, E> mapEntity = new HashMap<>();

    public void add(E entity) {
        mapEntity.put(entity.getId(), entity);
    }

    public void delete(String id) {
        mapEntity.remove(id);
    }

    public E getById(String id) {
        return mapEntity.get(id);
    }

    public List<E> getList() {
        return new ArrayList<>(mapEntity.values());
    }

    public E update(final E entity) {
        return mapEntity.put(entity.getId(), entity);
    }

}
