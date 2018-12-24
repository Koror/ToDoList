package com.koror.app.entity;

import java.util.UUID;

public class Group {

    private final String id = UUID.randomUUID().toString();

    private String name;

    public Group(String name) {
        this.name = name;
    }

    public void changeName(String newName) {
        name = newName;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + " id: " + id;
    }

}
