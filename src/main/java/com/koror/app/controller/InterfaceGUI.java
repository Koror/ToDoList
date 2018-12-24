package com.koror.app.controller;

import com.koror.app.entity.Group;
import com.koror.app.entity.Task;

import java.util.List;

public interface InterfaceGUI {

    Group addGroup();

    Task addTask();

    Task completeTask(List<Task> taskList);

    String deleteTask(List<Task> taskList);

    Task AddGroupToTask(List<Task> taskList, List<Group> groupList);

    String deleteGroup(List<Group> groupList);

    Group updateGroup(List<Group> groupList);

    Task updateTask(List<Task> taskList);

}
