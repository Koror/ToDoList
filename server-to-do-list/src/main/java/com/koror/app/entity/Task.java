package com.koror.app.entity;

import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tm_task")
public class Task extends AbstractEntity implements Serializable {

    @Setter
    @Getter
    @Basic
    private String groupId;

    @Setter
    @Getter
    @Basic
    private Priority priority;

    @Setter
    @Getter
    @Basic
    private String name;

    @Setter
    @Getter
    @Basic
    private boolean complete;

    @Setter
    @Getter
    @Basic
    private String creator;

    public Task(){
        
    }

    public Task(final String name) {
        this.name = name;
    }

    public Task(final String name, final Priority priority, final String groupId) {
        setName(name);
        this.priority = priority;
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Name:" + getName() + " Complete: " + complete;
    }

}
