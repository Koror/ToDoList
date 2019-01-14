package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.entity.AssigneeTask;

public class AssigneeTaskRepository extends AbstractRepository<AssigneeTask> implements IAssigneeTaskRepository {

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        for (AssigneeTask assigneeTask : mapEntity.values()) {
            if (userId.equals(assigneeTask.getUserId()) && taskId.equals(assigneeTask.getTaskId()))
                mapEntity.remove(assigneeTask.getId());
        }
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        for (AssigneeTask assigneeTask : mapEntity.values()) {
            if (assigneeTask.getUserId().equals(userId))
                return assigneeTask;
        }
        return null;
    }

}
