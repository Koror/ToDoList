package com.koror.app;

import java.util.ArrayList;

public class Group {
    private ArrayList<Task> taskList = new ArrayList<>();
    private String name;

    public Group(String name)
    {
        this.name = name;
    }

    public void addTask(String text,Priority priority)
    {
        Task task = new Task(text, priority);
        taskList.add(task);
    }

    public void completeTask(int index)
    {
        Task task = taskList.get(index);
        task.complete();
        taskList.set(index,task);
    }

    public boolean isCompleteTask(int index)
    {
        return taskList.get(index).isComplete();
    }
}
