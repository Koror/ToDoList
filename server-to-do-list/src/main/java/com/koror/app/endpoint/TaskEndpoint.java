package com.koror.app.endpoint;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.ITaskService;
import com.koror.app.api.service.IUserService;
import com.koror.app.dto.*;
import com.koror.app.entity.Group;
import com.koror.app.entity.Session;
import com.koror.app.entity.Task;
import com.koror.app.entity.User;
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

    @Inject
    private IUserService userService;

    @WebMethod
    public Result addTask(
            @WebParam(name = "name", partName = "name") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        final Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final User user = session.getUser();
        final Task task = new Task();
        task.setName(taskDTO.getName());
        task.setComplete(taskDTO.isComplete());
        task.setPriority(taskDTO.getPriority());
        if (taskDTO.getGroup() != null)
            task.setGroup(groupService.getById(taskDTO.getGroup().getId()));
        task.setCreator(user.getId());
        task.setUser(user);
        taskService.add(task, user);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clearTask(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        sessionService.validate(sessionDTO.getSignature());
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
        sessionService.validate(sessionDTO.getSignature());
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
        if (taskDTO == null || sessionDTO == null) throw new WrongDataException();
        sessionService.validate(sessionDTO.getSignature());
        Task task = taskService.getById(taskDTO.getId());
        taskService.delete(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<TaskDTO> getTaskList(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        List<TaskDTO> dtoList = new ArrayList<>();
        User user = userService.getById(session.getUser().getId());
        for (Task taskTemp : taskService.getListTaskByUserId(user)) {
            dtoList.add(new TaskDTO(taskTemp));
        }
        return dtoList;
    }

    @WebMethod
    public Result taskToGroupTask(
            @WebParam(name = "task", partName = "task") @Nullable TaskDTO taskDTO,
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
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
        sessionService.validate(sessionDTO.getSignature());
        Task task = taskService.getById(taskDTO.getId());
        task.setName(taskDTO.getName());
        task.setPriority(taskDTO.getPriority());
        taskService.update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result linkToTaskUser(
            @WebParam(name = "user", partName = "user") @Nullable UserDTO userDTO,
            @WebParam(name = "task", partName = "task") @Nullable TaskDTO taskDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        User user = userService.getByLogin(userDTO.getLogin());
        Task task = taskService.getById(taskDTO.getId());
        taskService.linkToTask(user, task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
