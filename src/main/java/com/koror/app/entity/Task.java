package com.koror.app.entity;

import com.koror.app.enumeration.Priority;

import java.util.UUID;

public class Task {

    private final String id = UUID.randomUUID().toString();

    private String groupId = null;
    private Priority priority;

    private String text;

    private boolean complete = false;

    public Task(String text, Priority priority) {
        this.text = text;
        this.priority = priority;
    }

    public Task(String text, Priority priority, String groupId) {
        this.text = text;
        this.priority = priority;
        this.groupId = groupId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void complete() {
        complete = true;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Name:" + text + " Priority: " + priority + " Complete: " + complete + " Group: "+groupId + " Id: " + id;
    }

    public void update(String text, Priority priority) {
        this.text = text;
        this.priority = priority;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
