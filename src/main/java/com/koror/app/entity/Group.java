package com.koror.app.entity;

import java.io.Serializable;
import java.util.UUID;

public class Group implements Serializable {

    private String id = UUID.randomUUID().toString();

    private String name = "Group name";

    private String creator = null;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return name;
    }

}
