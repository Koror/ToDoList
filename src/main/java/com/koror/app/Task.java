package com.koror.app;

public class Task {
    private Priority priority;
    private String text;
    private boolean complete=false;

    public Task(String text, Priority priority)
    {
        this.text = text;
        this.priority = priority;
    }

    public boolean isComplete()
    {
        return complete;
    }
    public void complete()
    {
        complete=true;
    }

}
