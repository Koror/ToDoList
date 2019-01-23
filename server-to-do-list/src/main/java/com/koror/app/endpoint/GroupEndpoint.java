package com.koror.app.endpoint;

import com.koror.app.controller.Bootstrap;
import com.koror.app.entity.*;
import com.koror.app.error.SessionNotValidateException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class GroupEndpoint {

    private Bootstrap bootstrap;

    public GroupEndpoint(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod
    public Result addGroup(
            @WebParam(name = "name", partName = "name") Group group,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final String userId = session.getUserId();
        bootstrap.getGroupService().add(group);
        final AssigneeGroup assigneeGroup = new AssigneeGroup(userId, group.getId());
        bootstrap.getAssigneeGroupService().add(assigneeGroup);
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result deleteGroup(
            @WebParam(name = "id", partName = "id") String groupId,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        final User user = bootstrap.getUserService().getById(session.getUserId());
        //delete project and assignee
        bootstrap.getAssigneeGroupService().deleteAssigneeByParam(user.getId(), groupId);
        bootstrap.getGroupService().delete(groupId);
        //delete all task and assignee in project
        for (AssigneeTask assigneeTask : bootstrap.getAssigneeTaskService().getList()) {
            Task taskTemp = bootstrap.getTaskService().getById(assigneeTask.getTaskId());
            if (groupId.equals(taskTemp.getGroupId())) {
                bootstrap.getTaskService().delete(taskTemp.getId());
                bootstrap.getAssigneeTaskService().delete(assigneeTask.getId());
            }
        }
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result updateGroup(
            @WebParam(name = "group", partName = "group") Group group,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        bootstrap.getGroupService().update(group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Group> getGroupList(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = bootstrap.getSessionService().validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        String userId = session.getUserId();
        final User user = bootstrap.getUserService().getById(userId);
        return bootstrap.getGroupService().getListGroupByUser(user);
    }

}
