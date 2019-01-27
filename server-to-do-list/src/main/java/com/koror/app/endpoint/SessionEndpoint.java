package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Session;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class SessionEndpoint {

    private SessionService sessionService;

    public SessionEndpoint(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @WebMethod
    public Result deleteSession(
            @WebParam(name = "id", partName = "id") String id,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        sessionService.delete(id);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteByUserSession(
            @WebParam(name = "user", partName = "user") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        sessionService.deleteByUserSession(userId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Session getByIdSession(
            @WebParam(name = "id", partName = "id") String id,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return sessionService.getById(id);
    }

    @WebMethod
    public List<Session> getListSession(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return sessionService.getList();
    }

    @WebMethod
    public Result updateSession(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        sessionService.update(session);
        final Result result = new Result();
        result.success();
        return result;
    }

}
