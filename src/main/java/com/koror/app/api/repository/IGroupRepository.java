package com.koror.app.api.repository;

import com.koror.app.entity.Group;

import java.io.IOException;
import java.util.List;

public interface IGroupRepository {

    void addGroup(final Group group);

    Group updateGroup(final Group group);

    Group deleteGroup(final String id);

    List<Group> getGroupList();

    Group getGroup(final Integer index);

    Group getGroupById(final String id);

    void saveDataSerialization() throws IOException;

    void loadDataSerialization()throws IOException, ClassNotFoundException;

    void saveDataXml() throws IOException;

    void loadDataXml() throws IOException;

    void saveDataJson() throws IOException;

    void loadDataJson() throws IOException;

}
