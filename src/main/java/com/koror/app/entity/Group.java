package com.koror.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;

    private final List<Task> taskList = new ArrayList<>();

    private final String name;

    public Group(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        return name;
    }

}
