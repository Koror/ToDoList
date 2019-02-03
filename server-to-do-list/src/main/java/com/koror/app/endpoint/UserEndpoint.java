package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.dto.SessionDTO;
import com.koror.app.dto.TaskDTO;
import com.koror.app.dto.UserDTO;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.Session;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.SessionService;
import com.koror.app.service.TaskService;
import com.koror.app.service.UserService;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class UserEndpoint {

    @Inject
    private UserService userService;

    @Inject
    private SessionService sessionService;

    @Inject
    private TaskService taskService;

    @WebMethod
    public Result deleteUser(
            @WebParam(name = "user", partName = "user") @Nullable UserDTO userDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final Result result = new Result();
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        User user = userService.getByLogin(userDTO.getLogin());
        if(user == null) return result;
        userService.delete(user);
        for (Session sessionTemp : sessionService.getList()) {
            if (user.getId().equals(sessionTemp.getUser().getId()))
                sessionService.delete(sessionTemp);
        }
        result.success();
        return result;
    }

    @WebMethod
    public SessionDTO loginUser(
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
        return new SessionDTO(session);
    }

    @WebMethod
    public Result logoutUser(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final Result result = new Result();
        if (session == null) return result;
        sessionService.delete(session);
        result.success();
        return result;
    }

    @WebMethod
    public List<UserDTO> getUserList(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final Result result = new Result();
        result.success();
        List<UserDTO> dtoList = new ArrayList<>();
        for(User userTemp: userService.getList()){
            dtoList.add(new UserDTO(userTemp));
        }
        return dtoList;
    }

    @WebMethod
    public UserDTO getUserById(
            @WebParam(name = "user", partName = "user") @Nullable String userId,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return new UserDTO(userService.getById(userId));
    }

    @WebMethod
    public SessionDTO registerUser(
            @WebParam(name = "login", partName = "login") @Nullable String login,
            @WebParam(name = "password", partName = "password") @Nullable String password,
            @WebParam(name = "ip", partName = "ip") @Nullable String ip) {
        User user = new User(login, password);
        userService.add(user);
        Session session = new Session();
        session.setIp(ip);
        session.setUser(user);
        session.hashSignature();
        sessionService.add(session);
        return new SessionDTO(session);
    }

}
