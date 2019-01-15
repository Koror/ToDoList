package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.*;

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
    public Result add(@WebParam( name = "name", partName = "name")Task task, @WebParam( name = "session", partName = "session") Session session){
        final String userId = session.getUserId();
        task.setCreator(userId);
        bootstrap.getTaskService().add(task);
        final AssigneeTask assigneeTask = new AssigneeTask(userId, task.getId());
        bootstrap.getAssigneeTaskService().add(assigneeTask);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result clear(@WebParam( name = "session", partName = "session") Session session){
        final String userId = session.getUserId();
        final User user = bootstrap.getUserService().getById(userId);
        List<Task> taskList = bootstrap.getTaskService().getListTaskByUser(user);
        bootstrap.getTaskService().clearTask(taskList);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result complete(@WebParam( name = "id", partName = "id")String taskId){
        final Task task = bootstrap.getTaskService().getById(taskId);
        task.setComplete(true);
        bootstrap.getTaskService().completeTask(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result delete(@WebParam( name = "id", partName = "id")String taskId, @WebParam( name = "session", partName = "session") Session session){
        final String userId = session.getUserId();
        final User user = bootstrap.getUserService().getById(userId);
        bootstrap.getAssigneeTaskService().deleteAssigneeByParam(user.getId(), taskId);
        bootstrap.getTaskService().delete(taskId);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Task> readAll(@WebParam( name = "session", partName = "session") Session session){
        String userId = session.getUserId();
        final User user = bootstrap.getUserService().getById(userId);
        bootstrap.getTaskService().getListTaskByUser(user);
        return bootstrap.getTaskService().getListTaskByUser(user);
    }

    @WebMethod
    public Result taskToGroup(
            @WebParam( name = "idTask", partName = "idTask")String idTask,
            @WebParam( name = "idGroup", partName = "idGroup")String idGroup){
        final Task task = bootstrap.getTaskService().getById(idTask);
        final Group group = bootstrap.getGroupService().getById(idGroup);
        task.setGroupId(group.getId());
        bootstrap.getTaskService().setGroupId(task);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public Result update(
            @WebParam( name = "task", partName = "task")Task task){
        bootstrap.getTaskService().update(task);
        final Result result = new Result();
        result.success();
        return result;
    }

}
