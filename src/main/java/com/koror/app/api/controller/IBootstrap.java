package com.koror.app.api.controller;

import com.koror.app.command.AbstractCommand;
import com.koror.app.entity.Session;
import com.koror.app.service.*;

import java.io.IOException;
import java.util.Map;

public interface IBootstrap {

    GroupService getGroupService();

    TaskService getTaskService();

    UserService getUserService();

    AssigneeGroupService getAssigneeGroupService();

    AssigneeTaskService getAssigneeTaskService();

    SessionService getSessionService();

    Map<String, AbstractCommand> getCommandUserMap();

    Map<String, AbstractCommand> getCommandLoginMap();

    Integer nextInt();

    String nextLine();

}
