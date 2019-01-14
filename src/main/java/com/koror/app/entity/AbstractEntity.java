package com.koror.app.entity;

import java.util.UUID;

public abstract class AbstractEntity {

    private String id = UUID.randomUUID().toString();

    public AbstractEntity() {

    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
