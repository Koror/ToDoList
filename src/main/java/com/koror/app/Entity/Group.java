package com.koror.app.Entity;

import java.util.ArrayList;

public class Group {
    private ArrayList<Task> taskList = new ArrayList<>();
    private String name;

    public Group(String name)
    {
        this.name = name;
    }

    public ArrayList<Task> getTaskList()
    {
        return taskList;
    }

    @Override
    public String toString() {
        return name;
    }
}
