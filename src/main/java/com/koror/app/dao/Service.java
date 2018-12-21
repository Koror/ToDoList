package com.koror.app.dao;

import com.koror.app.entity.Group;

import java.util.List;

public abstract class Service {

    public abstract void addGroup();

    public abstract void addTask();

    public abstract void completeTask();

    public abstract void clear();

    public abstract void deleteTask();

    public abstract void deleteGroup();

    public abstract void updateGroup();

    public abstract void updateTask();

    public abstract void readAll();
}
