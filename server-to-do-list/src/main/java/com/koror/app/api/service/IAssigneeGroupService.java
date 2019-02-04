package com.koror.app.api.service;

import com.koror.app.entity.AssigneeGroup;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface IAssigneeGroupService {

    AssigneeGroup getAssigneeByUserId(@Nullable String userId);

    void delete(@Nullable String id);

    AssigneeGroup getById(@Nullable String id);

    List<AssigneeGroup> getList();

    void deleteAssigneeByParam(@Nullable String userId, @Nullable String groupId);

}
