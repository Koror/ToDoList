package com.koror.app.endpoint;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.dto.GroupDTO;
import com.koror.app.dto.SessionDTO;
import com.koror.app.dto.TaskDTO;
import com.koror.app.entity.*;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.error.WrongDataException;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class TaskEndpoint {

    @Inject
    private ITaskService taskService;

    @Inject
    private IGroupService groupService;

    @Inject
    private ISessionService sessionService;

    @WebMethod
    public Result addTask(
            @WebParam(name = "name", partName = "name") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setComplete(taskDTO.isComplete());
        task.setPriority(taskDTO.getPriority());
        if (taskDTO.getGroup() != null)
            task.setGroup(groupService.getById(taskDTO.getGroup().getId()));
        task.setCreator(session.getUser().getId());
        task.setUser(session.getUser());
        taskService.add(task, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clearTask(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        final User user = session.getUser();
        final List<Task> taskList = taskService.getListTaskByUserId(user);
        taskService.clearTask(taskList);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result completeTask(
            @WebParam(name = "id", partName = "id") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        Task task = taskService.getById(taskDTO.getId());
        task.setComplete(true);
        taskService.completeTask(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result deleteTask(
            @WebParam(name = "task", partName = "task") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        if(taskDTO == null || sessionDTO == null) throw new WrongDataException();
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        Task task = taskService.getById(taskDTO.getId());
        final User user = session.getUser();
        taskService.delete(task, user);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<TaskDTO> getTaskList(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        List<TaskDTO> dtoList = new ArrayList<>();
        for (Task taskTemp : taskService.getListTaskByUserId(session.getUser())) {
            dtoList.add(new TaskDTO(taskTemp));
        }
        return dtoList;
    }

    @WebMethod
    public Result taskToGroupTask(
            @WebParam(name = "task", partName = "task") @Nullable TaskDTO taskDTO,
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        Task task = taskService.getById(taskDTO.getId());
        Group group = groupService.getById(groupDTO.getId());
        taskService.setGroup(task, group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result updateTask(
            @WebParam(name = "task", partName = "task") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        Task task = taskService.getById(taskDTO.getId());
        taskService.update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
