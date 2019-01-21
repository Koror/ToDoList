package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.Session;
import com.koror.app.error.SessionNotValidateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class SessionEndpoint {

    private Bootstrap bootstrap;

    public SessionEndpoint(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public Result deleteSession(
            @WebParam(name = "id", partName = "id") String id,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getSessionService().delete(id);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteByUserSession(
            @WebParam(name = "userId", partName = "userId") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getSessionService().deleteByUserSession(userId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Session getByIdSession(
            @WebParam(name = "id", partName = "id") String id,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return bootstrap.getSessionService().getById(id);
    }

    @WebMethod
    public List<Session> getListSession(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return bootstrap.getSessionService().getList();
    }

    @WebMethod
    public Result updateSession(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getSessionService().update(session);
        final Result result = new Result();
        result.success();
        return result;
    }

}
