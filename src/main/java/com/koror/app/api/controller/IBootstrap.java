package com.koror.app.api.controller;

import com.koror.app.security.Authorization;
import com.koror.app.service.*;

import java.io.IOException;

public interface IBootstrap {

    void start() throws ReflectiveOperationException, IOException;

    GroupService getGroupService();

    TaskService getTaskService();

    UserService getUserService();

    AssigneeGroupService getAssigneeGroupService();

    AssigneeTaskService getAssigneeTaskService();

    Authorization getAuthorization();

    Integer nextInt();

    String nextLine();

}
