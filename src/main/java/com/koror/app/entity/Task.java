package com.koror.app.entity;

import java.util.UUID;

public class Task {

    private final String id = UUID.randomUUID().toString();

    private final String priority;

    private final String text;

    private boolean complete = false;

    public Task(String text, String priority) {
        this.text = text;
        this.priority = priority;
    }

    public boolean isComplete() {
        return complete;
    }

    public void complete() {
        complete = true;
    }

    @Override
    public String toString() {
        return "Name:" + text + " Priority: " + priority + " Complete: " + complete + " Id: " + id;
    }

}
