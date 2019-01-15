package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.AssigneeTask;
import com.koror.app.entity.User;
import com.koror.app.entity.Session;

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
    public Result delete(@WebParam(name = "id", partName = "id") String userId) {
        bootstrap.getUserService().delete(userId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Session login(@WebParam(name = "login", partName = "login") String login,
                        @WebParam(name = "password", partName = "password") String password) {
        String userId = bootstrap.getUserService().login(login, password).getId();
        Session session = new Session();
        session.setUserId(userId);
        bootstrap.getSessionService().add(session);
        return session;
    }

    @WebMethod
    public Result logout(@WebParam(name = "id", partName = "id") String userId) {
        bootstrap.getSessionService().delete(userId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<User> read() {
        final Result result = new Result();
        result.success();
        return bootstrap.getUserService().getList();
    }

    @WebMethod
    public Result add(@WebParam(name = "login", partName = "login") String login,
                      @WebParam(name = "password", partName = "password") String password) {
        final User user = new User(login, password);
        bootstrap.getUserService().add(user);
        Session session = new Session();
        session.setUserId(user.getId());
        bootstrap.getSessionService().add(session);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result linkToTask(@WebParam(name = "userId", partName = "userId") String userId,
                             @WebParam(name = "taskId", partName = "taskId") String taskId) {
        final AssigneeTask assigneeTask = new AssigneeTask(userId, taskId);
        bootstrap.getAssigneeTaskService().add(assigneeTask);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<User> getList(){
        final Result result = new Result();
        result.success();
        return bootstrap.getUserService().getList();
    }

}
