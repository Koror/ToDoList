package com.koror.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.jetbrains.annotations.NotNull;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity{

    @NotNull
    @Setter
    @Getter
    @Id
    protected String id = UUID.randomUUID().toString();

}
