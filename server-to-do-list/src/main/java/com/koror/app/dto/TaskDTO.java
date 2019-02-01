package com.koror.app.dto;

import com.koror.app.entity.Task;
import com.koror.app.enumerated.Priority;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Priority priority;

    private String name;

    private boolean complete;

    private String id;

    private GroupDTO group;

    public TaskDTO(Task task) {
        name = task.getName();
        priority = task.getPriority();
        complete = task.isComplete();
        id = task.getId();
        if (task.getGroup() != null)
            group = new GroupDTO(task.getGroup());
    }

}
