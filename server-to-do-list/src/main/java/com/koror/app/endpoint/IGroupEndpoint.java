package com.koror.app.endpoint;

import com.koror.app.dto.GroupDTO;
import com.koror.app.dto.Result;
import com.koror.app.dto.SessionDTO;
import com.koror.app.entity.Group;
import com.koror.app.entity.Session;
import com.koror.app.entity.User;
import org.jetbrains.annotations.Nullable;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public interface IGroupEndpoint {

    Result addGroup(@Nullable GroupDTO groupDTO, @Nullable SessionDTO sessionDTO);

    Result deleteGroup(@Nullable GroupDTO groupDTO, SessionDTO sessionDTO);

    Result updateGroup(@Nullable GroupDTO groupDTO, SessionDTO sessionDTO);

    List<GroupDTO> getGroupList();

}
