package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public abstract class AbstractEntity{

    @Setter
    @Getter
    private String id = UUID.randomUUID().toString();

}
