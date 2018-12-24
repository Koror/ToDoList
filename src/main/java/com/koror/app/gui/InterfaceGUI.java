package com.koror.app.gui;

import com.koror.app.entity.Group;

import java.util.List;

public interface InterfaceGUI {

    void addGroup();

    void addTask();

    void completeTask();

    void deleteTask();

    void deleteGroup();

    void updateGroup();

    void updateTask();

    void readAll(List<Group> groupList);

    void readAllGroup(List<Group> groupList);

    void readGroup(List<Group> groupList);

    int getIndexGroup();

    int getIndexTask();

    String getNameTask();

    String getNameGroup();

    String getPriority();
}
