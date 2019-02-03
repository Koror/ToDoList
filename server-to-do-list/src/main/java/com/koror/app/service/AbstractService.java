package com.koror.app.service;

import com.koror.app.entity.AbstractEntity;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import java.util.List;

@Transactional
public abstract class AbstractService<R extends EntityRepository, E extends AbstractEntity> {

    @Inject
    protected R repository;

    public AbstractService() {

    }

    public void add(@Nullable final E entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.save(entity);
    }

    public void update(@Nullable final E entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        repository.saveAndFlushAndRefresh(entity);
    }

    public List<E> getList() {
        return repository.findAll();
    }
}
