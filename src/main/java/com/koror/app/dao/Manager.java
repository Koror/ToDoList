package com.koror.app.dao;

import com.koror.app.entity.Group;

import java.util.ArrayList;

public interface Manager {

    void addGroup();

    void addTask();

    void completeTask();

    void deleteTask();

    void deleteGroup();

    void updateTask();

    void readAll(ArrayList<Group> list);
}
