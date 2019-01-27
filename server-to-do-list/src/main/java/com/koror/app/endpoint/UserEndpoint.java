package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Session;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.SessionService;
import com.koror.app.service.UserService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class UserEndpoint {

    private UserService userService;

    private SessionService sessionService;

    public UserEndpoint(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public Result deleteUser(
            @WebParam(name = "id", partName = "id") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession =sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        userService.delete(userId);
        for (Session sessionTemp : sessionService.getList()) {
            if (userId.equals(sessionTemp.getUser()))
                sessionService.delete(sessionTemp.getId());
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
        final User user = userService.login(login, password);
        Session session = null;
        for (Session sessionTemp : sessionService.getList()) {
            if ((user.getId().equals(sessionTemp.getUser())) && (ip.equals(sessionTemp.getIp())))
                session = sessionTemp;
        }
        if (session == null) {
            session = new Session();
            session.setUser(user);
            session.setIp(ip);
            sessionService.add(session);
        }
        return session;
    }

    @WebMethod
    public Result logoutUser(@WebParam(name = "session", partName = "session") Session session) {
        sessionService.delete(session.getId());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<User> getUserList(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final Result result = new Result();
        result.success();
        return userService.getList();
    }

    @WebMethod
    public User getUserById(
            @WebParam(name = "user", partName = "user") String userId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return userService.getById(userId);
    }

    @WebMethod
    public Session registerUser(
            @WebParam(name = "user", partName = "user") User user,
            @WebParam(name = "ip", partName = "ip") String ip) {
        userService.add(user);
        Session session = new Session();
        session.setIp(ip);
        session.setUser(user);
        sessionService.add(session);
        return session;
    }

    @WebMethod
    public Result linkToTaskUser(
            @WebParam(name = "user", partName = "user") User user,
            @WebParam(name = "task", partName = "task") Task task,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        userService.linkToTask(user, task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
