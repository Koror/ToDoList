package com.koror.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private final String id = UUID.randomUUID().toString();;

    private final List<Task> taskList = new ArrayList<>();

    private String name;

    public Group(String name) {
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void changeName(String newName)
    {
        name = newName;
    }

    @Override
    public String toString() {
        return name;
    }

}
