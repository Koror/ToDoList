package com.koror.app.api.controller;

import com.koror.app.command.AbstractCommand;
import com.koror.app.security.Authorization;
import com.koror.app.service.*;

import java.io.IOException;
import java.util.Map;

public interface IBootstrap {

    void start() throws ReflectiveOperationException, IOException;

    GroupService getGroupService();

    TaskService getTaskService();

    UserService getUserService();

    AssigneeGroupService getAssigneeGroupService();

    AssigneeTaskService getAssigneeTaskService();

    Authorization getAuthorization();

    Map<String, AbstractCommand> getCommandUserMap();

    Map<String, AbstractCommand> getCommandLoginMap();

    Integer nextInt();

    String nextLine();

}
