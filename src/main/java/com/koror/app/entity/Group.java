package com.koror.app.entity;

import java.util.UUID;

public class Group {

    private String id = UUID.randomUUID().toString();

    private String name;

    public Group() {
        this.name = "Group Name";
    }

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

}
