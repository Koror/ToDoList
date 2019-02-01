package com.koror.app.dto;

import com.koror.app.entity.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class GroupDTO {

    private String id;

    private String name;

    private String creator;

    public GroupDTO(Group group){
        name = group.getName();
        id = group.getId();
        creator = group.getCreator();
    }
}
