package com.koror.app.dao;

import com.koror.app.entity.Group;

import java.util.ArrayList;

public abstract class TaskManager {

    public abstract void addGroup();

    public abstract void addTask();

    public abstract void completeTask();

    public abstract void clear();

    public abstract void readAll();

    public abstract ArrayList<Group> getGroupList();
}
