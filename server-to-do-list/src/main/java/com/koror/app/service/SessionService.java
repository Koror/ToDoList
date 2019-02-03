package com.koror.app.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.api.service.ISessionService;
import com.koror.app.entity.Session;
import com.koror.app.error.WrongInputException;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@Transactional
@ApplicationScoped
public class SessionService extends AbstractService<ISessionRepository, Session> implements ISessionService {

    @Override
    public Session getBySignature(@Nullable String signature){
        if (signature == null) throw new WrongInputException("Wrong Input");
        return repository.findBySignature(signature);
    }

    @Override
    public void deleteByUserId(@Nullable String userId){
        if (userId == null) throw new WrongInputException("Wrong Input");
        for (Session sessionTemp : repository.findAll()) {
            if(sessionTemp.getUser() == null) throw new WrongInputException("Wrong Input");
            if (userId.equals(sessionTemp.getUser().getId()))
                repository.remove(sessionTemp);
        }
    }

    public Session getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return repository.findBy(id);
    }

    public void delete(@Nullable Session entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        repository.remove(entity);
    }

    @Override
    public boolean validate(@Nullable Session session) {
        if (session == null) throw new WrongInputException("Wrong Input");
        for (Session sessionTemp : getList()) {
            if (session.equals(sessionTemp))
                return true;
        }
        return false;
    }

}
