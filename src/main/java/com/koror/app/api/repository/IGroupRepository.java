package com.koror.app.api.repository;

import com.koror.app.entity.Group;

import java.io.IOException;
import java.util.List;

public interface IGroupRepository extends IRepository<Group>{

    Group getGroupByIndex(final Integer index);

}
