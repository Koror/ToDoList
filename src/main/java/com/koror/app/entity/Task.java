package com.koror.app.entity;

import com.koror.app.enumerated.Priority;

import java.io.Serializable;
import java.util.UUID;

public class Task extends AbstractEntity implements Serializable {

    private String groupId = null;

    private Priority priority = Priority.MEDIUM;

    private String name = "Default name";

    private boolean complete = false;

    private String creator = null;

    public Task(){
        
    }

    public Task(final String name) {
        this.name = name;
    }

    public Task(final String name, final Priority priority) {
        setName(name);
        this.priority = priority;
    }

    public Task(final String name, final Priority priority, final String groupId) {
        setName(name);
        this.priority = priority;
        this.groupId = groupId;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
