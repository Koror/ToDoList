package com.koror.app.endpoint;

import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.entity.*;
import com.koror.app.error.SessionNotValidateException;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class TaskEndpoint {

    @Inject
    private ITaskService taskService;

    @Inject
    private ISessionService sessionService;

    @WebMethod
    public Result addTask(
            @WebParam(name = "name", partName = "name") @Nullable Task task,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        taskService.add(task, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clearTask(@WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        final User user = session.getUser();
        final List<Task> taskList = taskService.getListTaskByUserId(user.getId());
        taskService.clearTask(taskList);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result completeTask(
            @WebParam(name = "id", partName = "id") @Nullable String taskId,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final Task task = taskService.getById(taskId);
        task.setComplete(true);
        taskService.completeTask(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteTask(
            @WebParam(name = "id", partName = "id") @Nullable String taskId,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        final User user = session.getUser();
        taskService.delete(taskId, user.getId());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Task> getTaskList(@WebParam(name = "session", partName = "session") @Nullable Session session){
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        User user = session.getUser();
        return taskService.getListTaskByUserId(user.getId());
    }

    @WebMethod
    public Result taskToGroupTask(
            @WebParam(name = "task", partName = "task") @Nullable Task task,
            @WebParam(name = "group", partName = "group") @Nullable Group group,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        taskService.setGroup(task, group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result updateTask(
            @WebParam(name = "task", partName = "task") @Nullable Task task,
            @WebParam(name = "session", partName = "session") @Nullable Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        taskService.update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
