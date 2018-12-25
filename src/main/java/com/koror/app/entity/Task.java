package com.koror.app.entity;

import com.koror.app.enumeration.Priority;

import java.util.UUID;

public class Task {

    private String id = UUID.randomUUID().toString();

    private String groupId = null;

    private Priority priority;

    private String text;

    private boolean complete = false;

    public Task() {
        text = "Task Name";
    }

    public Task(String text) {
        this.text = text;
    }

    public Task(String text, Priority priority) {
        this.text = text;
        this.priority = priority;
    }

    public Task(String text, Priority priority, String groupId) {
        this.text = text;
        this.priority = priority;
        this.groupId = groupId;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete() {
        complete = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Name:" + text  + " Complete: " + complete;
    }

}
