package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public class Task extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    private String groupId;

    @Setter
    @Getter
    private Priority priority;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private boolean complete;

    @Setter
    @Getter
    private String creator;

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

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
