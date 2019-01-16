package com.koror.app.entity;

import java.io.Serializable;
import java.util.UUID;

public class Group extends AbstractEntity implements Serializable {

    private String creator = null;

    private String name = "Default name";

    public Group(){

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return getName();
    }

}
