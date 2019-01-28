package com.koror.app.endpoint;

import com.koror.app.entity.*;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.AssigneeTaskService;
import com.koror.app.service.SessionService;
import com.koror.app.service.TaskService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class TaskEndpoint {

    private TaskService taskService;

    private SessionService sessionService;

    public TaskEndpoint(TaskService taskService, SessionService sessionService) {
        this.taskService = taskService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public Result addTask(@WebParam( name = "name", partName = "name")Task task, @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        taskService.add(task,session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clearTask(@WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final User user = session.getUser();
        final List<Task> taskList = taskService.getListTaskByUserId(user.getId());
        taskService.clearTask(taskList);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result completeTask(
            @WebParam( name = "id", partName = "id")String taskId,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession =sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final Task task =taskService.getById(taskId);
        task.setComplete(true);
        taskService.completeTask(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteTask(
            @WebParam( name = "id", partName = "id")String taskId,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        final User user = session.getUser();
        taskService.delete(taskId, user.getId());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Task> getTaskList(@WebParam( name = "session", partName = "session") Session session) throws SessionNotValidateException{
        final boolean validateSession =sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        User user = session.getUser();
        return taskService.getListTaskByUserId(user.getId());
    }

    @WebMethod
    public Result taskToGroupTask(
            @WebParam( name = "task", partName = "task")Task task,
            @WebParam( name = "group", partName = "group")Group group,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession = sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        taskService.setGroup(task, group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result updateTask(
            @WebParam( name = "task", partName = "task")Task task,
            @WebParam( name = "session", partName = "session") Session session){
        final boolean validateSession =sessionService.validate(session);
        if(!validateSession) throw new SessionNotValidateException();
        taskService.update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
