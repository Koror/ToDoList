package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.*;
import com.koror.app.error.SessionNotValidateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TaskEndpoint {

    private Bootstrap bootstrap;

    public TaskEndpoint(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public Result addTask(@WebParam( name = "name", partName = "name")Task task, @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        bootstrap.getTaskService().add(task);
        final AssigneeTask assigneeTask = new AssigneeTask(session.getUser(), task);
        bootstrap.getAssigneeTaskService().add(assigneeTask);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clearTask(@WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final String userId = session.getUser().getId();
        final User user = bootstrap.getUserService().getById(userId);
        List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
        bootstrap.getTaskService().clearTask(taskList);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result completeTask(
            @WebParam( name = "id", partName = "id")String taskId,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final Task task = bootstrap.getTaskService().getById(taskId);
        task.setComplete(true);
        bootstrap.getTaskService().completeTask(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteTask(
            @WebParam( name = "id", partName = "id")String taskId,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final String userId = session.getUser().getId();
        final User user = bootstrap.getUserService().getById(userId);
        bootstrap.getAssigneeTaskService().deleteAssigneeByParam(user.getId(), taskId);
        bootstrap.getTaskService().delete(taskId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Task> getTaskList(@WebParam( name = "session", partName = "session") Session session) throws SessionNotValidateException{
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        User user = session.getUser();
        return bootstrap.getTaskService().getListTaskByUserId(user.getId());
    }

    @WebMethod
    public Result taskToGroupTask(
            @WebParam( name = "idTask", partName = "idTask")String idTask,
            @WebParam( name = "idGroup", partName = "idGroup")String idGroup,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final Task task = bootstrap.getTaskService().getById(idTask);
        final Group group = bootstrap.getGroupService().getById(idGroup);
        bootstrap.getTaskService().setGroup(task, group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result updateTask(
            @WebParam( name = "task", partName = "task")Task task,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        bootstrap.getTaskService().update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
