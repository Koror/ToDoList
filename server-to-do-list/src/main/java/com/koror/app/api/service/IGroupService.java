package com.koror.app.api.service;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;
import com.koror.app.entity.User;

import java.util.List;

public interface IGroupService extends IGroupRepository {

    List<Group> getListGroupByUser(User user);

}
