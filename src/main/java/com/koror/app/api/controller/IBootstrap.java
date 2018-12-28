package com.koror.app.api.controller;

import com.koror.app.service.GroupService;
import com.koror.app.service.TaskService;

public interface IBootstrap {

    void start() throws ReflectiveOperationException;

    GroupService getGroupService();

    TaskService getTaskService();

    Integer nextInt();

    String nextLine();

}
