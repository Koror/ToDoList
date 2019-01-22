package com.koror.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tm_group")
public class Group extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Setter
    @Getter
    @Basic
    private String creator;

    @Setter
    @Getter
    @Basic
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
