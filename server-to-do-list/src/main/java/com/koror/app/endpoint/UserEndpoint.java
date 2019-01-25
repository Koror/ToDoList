package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Session;
import com.koror.app.entity.Task;
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
            if (userId.equals(sessionTemp.getUser()))
                bootstrap.getSessionService().delete(sessionTemp.getId());
        }
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Session loginUser(
            @WebParam(name = "login", partName = "login") String login,
            @WebParam(name = "password", partName = "password") String password,
            @WebParam(name = "ip", partName = "ip") String ip) {
        final User user = bootstrap.getUserService().login(login, password);
        Session session = null;
        for (Session sessionTemp : bootstrap.getSessionService().getList()) {
            if ((user.getId().equals(sessionTemp.getUser())) && (ip.equals(sessionTemp.getIp())))
                session = sessionTemp;
        }
        if (session == null) {
            session = new Session();
            session.setUser(user);
            session.setIp(ip);
            bootstrap.getSessionService().add(session);
        }
        return session;
    }

    @WebMethod
    public Result logoutUser(@WebParam(name = "session", partName = "session") Session session) {
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
            @WebParam(name = "user", partName = "user") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return bootstrap.getUserService().getById(userId);
    }

    @WebMethod
    public Session registerUser(
            @WebParam(name = "user", partName = "user") User user,
            @WebParam(name = "ip", partName = "ip") String ip) {
        bootstrap.getUserService().add(user);
        Session session = new Session();
        session.setIp(ip);
        session.setUser(user);
        bootstrap.getSessionService().add(session);
        return session;
    }

    @WebMethod
    public Result linkToTaskUser(
            @WebParam(name = "user", partName = "user") User user,
            @WebParam(name = "task", partName = "task") Task task,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        task.setUser(user);
        final AssigneeTask assigneeTask = new AssigneeTask(user, task);
        bootstrap.getAssigneeTaskService().add(assigneeTask);
        final Result result = new Result();
        result.success();
        return result;
    }

}
