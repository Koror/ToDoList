package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {

    @NotNull
    @Setter
    @Getter
    @Id
    protected String id = UUID.randomUUID().toString();

}
