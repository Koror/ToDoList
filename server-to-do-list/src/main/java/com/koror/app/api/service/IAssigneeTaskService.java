package com.koror.app.api.service;

import com.koror.app.entity.AssigneeTask;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IAssigneeTaskService {

    AssigneeTask getAssigneeByUserId(@Nullable String userId);

    void delete(@Nullable String id);

    AssigneeTask getById(@Nullable String id);

    List<AssigneeTask> getList();

    void deleteAssigneeByParam(@Nullable String userId, @Nullable String groupId);

}
