package com.koror.app.entity;

import java.util.UUID;

public class Group {

    private String id = UUID.randomUUID().toString();

    private String name = "Group name";

    public Group() {

    }

    public Group(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

}
