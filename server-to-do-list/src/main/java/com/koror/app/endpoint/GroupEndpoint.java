package com.koror.app.endpoint;

import com.koror.app.api.service.IGroupService;
import com.koror.app.api.service.ISessionService;
import com.koror.app.api.service.IUserService;
import com.koror.app.dto.GroupDTO;
import com.koror.app.dto.Result;
import com.koror.app.dto.SessionDTO;
import com.koror.app.entity.Group;
import com.koror.app.entity.Session;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@Component
@WebService
public class GroupEndpoint {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private ISessionService sessionService;

    @Autowired
    private IUserService userService;

    @WebMethod
    public Result addGroup(
            @WebParam(name = "name", partName = "name") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        User user = session.getUser();
        Group group = new Group();
        group.setName(groupDTO.getName());
        group.setCreator(user.getId());
        group.setUser(user);
        groupService.add(group, user);
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result deleteGroup(
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        Group group = groupService.getById(groupDTO.getId());
        groupService.delete(group);
        final Result result = new Result();
        result.success();
        return result;
    }

    public Result updateGroup(
            @WebParam(name = "group", partName = "group") @Nullable GroupDTO groupDTO,
            @WebParam(name = "session", partName = "session") SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        Group group = groupService.getById(groupDTO.getId());
        group.setName(groupDTO.getName());
        groupService.update(group);
        final Result result = new Result();
        result.success();
        return result;
    }

    @WebMethod
    public List<GroupDTO> getGroupList(@WebParam(name = "session", partName = "session") @Nullable SessionDTO sessionDTO) {
        sessionService.validate(sessionDTO.getSignature());
        Session session = sessionService.getBySignature(sessionDTO.getSignature());
        List<GroupDTO> dtoList = new ArrayList<>();
        User user = userService.getById(session.getUser().getId());
        for (Group groupTemp : groupService.getListGroupByUserId(user)) {
            dtoList.add(new GroupDTO(groupTemp));
        }
        return dtoList;
    }

}
