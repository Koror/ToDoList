package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Session;
import com.koror.app.entity.User;
import com.koror.app.error.SessionNotValidateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserEndpoint {

    private Bootstrap bootstrap;

    public UserEndpoint(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public Result deleteUser(
            @WebParam(name = "id", partName = "id") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getUserService().delete(userId);
        for (Session sessionTemp : bootstrap.getSessionService().getList()) {
            if (userId.equals(sessionTemp.getUserId()))
                bootstrap.getSessionService().delete(sessionTemp.getId());
        }
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Session loginUser(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password) {
        final User user = bootstrap.getUserService().login(login, password);
        Session session = null;
        for (Session sessionTemp : bootstrap.getSessionService().getList()) {
            if (user.equals(sessionTemp.getUserId()))
                session = sessionTemp;
        }
        if (session == null)
            session = new Session(user.getId());
        bootstrap.getSessionService().add(session);
        return session;
    }

    @WebMethod
    public Result logoutUser(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getSessionService().delete(session.getId());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<User> getUserList(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final Result result = new Result();
        result.success();
        return bootstrap.getUserService().getList();
    }

    @WebMethod
    public User getUserById(
            @WebParam(name = "userId", partName = "userId") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return bootstrap.getUserService().getById(userId);
    }

    @WebMethod
    public Session registerUser(@WebParam(name = "user", partName = "user") User user) {
        bootstrap.getUserService().add(user);
        Session session = new Session(user.getId());
        bootstrap.getSessionService().add(session);
        return session;
    }

    @WebMethod
    public Result linkToTaskUser(
            @WebParam(name = "userId", partName = "userId") String userId,
            @WebParam(name = "taskId", partName = "taskId") String taskId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final AssigneeTask assigneeTask = new AssigneeTask(userId, taskId);
        bootstrap.getAssigneeTaskService().add(assigneeTask);
        final Result result = new Result();
        result.success();
        return result;
    }

}
