package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Session;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.SessionService;
import com.koror.app.service.UserService;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private SessionService sessionService;

    @WebMethod
    public Result deleteUser(
            @WebParam(name = "id", partName = "id") @Nullable String userId,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final Result result = new Result();
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        if(userId == null) return result;
        userService.delete(userId);
        for (Session sessionTemp : sessionService.getList()) {
            if (userId.equals(sessionTemp.getUser().getId()))
                sessionService.delete(sessionTemp.getId());
        }
        result.success();
        return result;
    }

    @WebMethod
    public Session loginUser(
            @WebParam(name = "login", partName = "login") @Nullable String login,
            @WebParam(name = "password", partName = "password") @Nullable String password,
            @WebParam(name = "ip", partName = "ip") String ip) {
        final User user = userService.login(login, password);
        Session session = null;
        for (Session sessionTemp : sessionService.getList()) {
            if ((user.getId().equals(sessionTemp.getUser().getId())) && (ip.equals(sessionTemp.getIp())))
                session = sessionTemp;
        }
        if (session == null) {
            session = new Session();
            session.setUser(user);
            session.setIp(ip);
            session.hashSignature();
            sessionService.add(session);
        }
        return session;
    }

    @WebMethod
    public Result logoutUser(@WebParam(name = "session", partName = "session") @Nullable Session session) {
        final Result result = new Result();
        if (session == null) return result;
        sessionService.delete(session.getId());
        result.success();
        return result;
    }

    @WebMethod
    public List<User> getUserList(@WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final Result result = new Result();
        result.success();
        return userService.getList();
    }

    @WebMethod
    public User getUserById(
            @WebParam(name = "user", partName = "user") @Nullable String userId,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return userService.getById(userId);
    }

    @WebMethod
    public Session registerUser(
            @WebParam(name = "user", partName = "user") @Nullable User user,
            @WebParam(name = "ip", partName = "ip") @Nullable String ip) {
        userService.add(user);
        Session session = new Session();
        session.setIp(ip);
        session.setUser(user);
        session.hashSignature();
        sessionService.add(session);
        return session;
    }

    @WebMethod
    public Result linkToTaskUser(
            @WebParam(name = "user", partName = "user") @Nullable User user,
            @WebParam(name = "task", partName = "task") @Nullable Task task,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        userService.linkToTask(user, task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
