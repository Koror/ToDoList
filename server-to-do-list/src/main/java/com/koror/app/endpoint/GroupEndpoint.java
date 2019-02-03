package com.koror.app.endpoint;

import com.koror.app.dto.GroupDTO;
import com.koror.app.dto.SessionDTO;
import com.koror.app.entity.Group;
import com.koror.app.entity.Session;
import com.koror.app.error.SessionNotValidateException;
import com.koror.app.service.GroupService;
import com.koror.app.service.SessionService;
import com.koror.app.service.TaskService;
import org.jetbrains.annotations.Nullable;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class GroupEndpoint {

    @Inject
    private GroupService groupService;

    @Inject
    private SessionService sessionService;

    @WebMethod
    public Result addGroup(
            @WebParam(name = "name", partName = "name") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession || session.getUser() == null) throw new SessionNotValidateException();
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setCreator(session.getUser().getId());
        group.setUser(session.getUser());
        groupService.add(group, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result deleteGroup(
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        Group group = groupService.getById(groupDTO.getId());
        groupService.delete(group, session.getUser());
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result updateGroup(
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (!validateSession) throw new SessionNotValidateException();
        Group group = groupService.getById(groupDTO.getId());
        group.setName(groupDTO.getName());
        groupService.update(group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<GroupDTO> getGroupList(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        final boolean validateSession = sessionService.validate(session);
        if (session == null || !validateSession) throw new SessionNotValidateException();
        List<GroupDTO> dtoList = new ArrayList<>();
        for(Group groupTemp: groupService.getListGroupByUserId(session.getUser())){
            dtoList.add(new GroupDTO(groupTemp));
        }
        return dtoList;
    }

}
