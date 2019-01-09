package com.koror.app.api.controller;

import com.koror.app.security.Authorization;
import com.koror.app.service.*;

public interface IBootstrap {

    void start() throws ReflectiveOperationException;

    GroupService getGroupService();

    TaskService getTaskService();

    UserService getUserService();

    AssigneeGroupService getAssigneeGroupService();

    AssigneeTaskService getAssigneeTaskService();

    Authorization getAuthorization();

    Integer nextInt();

    String nextLine();

}
