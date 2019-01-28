package com.koror.app.endpoint;

import com.koror.app.entity.Group;
import com.koror.app.entity.Session;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.GroupService;
import com.koror.app.service.SessionService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public class GroupEndpoint {

    private GroupService groupService;

    private SessionService sessionService;

    public GroupEndpoint(GroupService groupService, SessionService sessionService) {
        this.groupService = groupService;
        this.sessionService = sessionService;
    }

    @WebMethod
    public Result addGroup(
            @WebParam(name = "name", partName = "name") Group group,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        groupService.add(group, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result deleteGroup(
            @WebParam(name = "group", partName = "group") Group group,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        groupService.delete(group, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result updateGroup(
            @WebParam(name = "group", partName = "group") Group group,
            @WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        groupService.update(group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<Group> getGroupList(@WebParam(name = "session", partName = "session") Session session) {
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        return groupService.getListGroupByUserId(session.getUser());
    }

}
