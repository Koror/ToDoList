package com.koror.app.entity;

import com.koror.app.enumerated.Priority;

import java.io.Serializable;
import java.util.UUID;

public class Task implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String groupId = null;

    private Priority priority = Priority.MEDIUM;

    private String text = "Task  name";

    private boolean complete = false;

    public Task() {

    }

    public Task(final String text) {
        this.text = text;
    }

    public Task(final String text, final Priority priority) {
        this.text = text;
        this.priority = priority;
    }

    public Task(final String text, final Priority priority, final String groupId) {
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

    public void setId(final String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(final String groupId) {
        this.groupId = groupId;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(final Priority priority) {
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Name:" + text + " Complete: " + complete;
    }

}
