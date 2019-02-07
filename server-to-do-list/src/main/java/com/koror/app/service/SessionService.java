package com.koror.app.service;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.api.service.ISessionService;
import com.koror.app.entity.Session;
import com.koror.app.entity.User;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.error.WrongInputException;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SessionService implements ISessionService {

    @Autowired
    private ISessionRepository iSessionRepository;

    @Override
    public void add(@Nullable final Session entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iSessionRepository.save(entity);
    }

    @Override
    public void update(@Nullable final Session entity) {
        if (entity == null) throw new WrongInputException("Wrong input");
        iSessionRepository.save(entity);
    }

    @Override
    public List<Session> getList() {
        return iSessionRepository.findAll();
    }

    @Override
    public Session getBySignature(@Nullable String signature) {
        if (signature == null) throw new WrongInputException("Wrong Input");
        return iSessionRepository.findBySignature(signature);
    }

    @Override
    public void deleteByUserId(@Nullable String userId) {
        if (userId == null) throw new WrongInputException("Wrong Input");
        for (Session sessionTemp : iSessionRepository.findAll()) {
            if (sessionTemp.getUser() == null) throw new WrongInputException("Wrong Input");
            if (userId.equals(sessionTemp.getUser().getId()))
                iSessionRepository.delete(sessionTemp);
        }
    }

    @Override
    public Session getById(@Nullable String id) {
        if (id == null || id.isEmpty()) throw new WrongInputException("Wrong Input");
        return iSessionRepository.findById(id).get();
    }

    @Override
    public void delete(@Nullable Session entity) {
        if (entity == null) throw new WrongInputException("Wrong Input");
        iSessionRepository.delete(entity);
    }

    @Override
    public Session login(User user, String ip) {
        Session session = null;
        for (Session sessionTemp : getList()) {
            if ((user.getId().equals(sessionTemp.getUser().getId())) && (ip.equals(sessionTemp.getIp())))
                session = sessionTemp;
        }
        if (session == null) {
            session = new Session();
            session.setUser(user);
            session.setIp(ip);
            session.hashSignature();
            add(session);
        }
        return session;
    }

    @Override
    public void validate(@Nullable String signature) {
        if (signature == null) throw new WrongInputException("Wrong Input");
        boolean validateSession = false;
        for (Session sessionTemp : getList()) {
            if (signature.equals(sessionTemp.getSignature()))
                validateSession = true;
        }
        if (!validateSession) throw new SessionNotValidateException();
    }

}
