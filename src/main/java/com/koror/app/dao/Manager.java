package com.koror.app.dao;

import com.koror.app.entity.Group;

import java.util.List;

public interface Manager {

    void addGroup();

    void addTask();

    void completeTask();

    void deleteTask();

    void deleteGroup();

    void updateTask();

    void readAll(List<Group> list);
}
