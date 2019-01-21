package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

public class Group extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    private String creator;

    @Setter
    @Getter
    private String name;

    public Group(){

    }

    public Group(final String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return getName();
    }

}
