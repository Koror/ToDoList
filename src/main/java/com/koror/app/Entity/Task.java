package com.koror.app.Entity;

public class Task {
    private String priority;
    private String text;
    private boolean complete=false;

    public Task(String text, String priority)
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

    @Override
    public String toString() {
        return "Name:"+text+" Priority: "+priority+" Complete: "+complete;
    }
}
