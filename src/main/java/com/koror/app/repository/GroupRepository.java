package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.entity.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupRepository implements IGroupRepository {

    private final Map<String, Group> groupMap = new HashMap<>();

    @Override
    public void addGroup(final Group group) {
        groupMap.put(group.getId(), group);
    }

    @Override
    public Group updateGroup(final Group group) {
        return groupMap.put(group.getId(), group);
    }

    @Override
    public Group deleteGroup(final String id) {
        return groupMap.remove(id);
    }

    @Override
    public List<Group> getGroupList() {
        return new ArrayList<>(groupMap.values());
    }

    @Override
    public Group getGroup(final Integer index) {
        return getGroupList().get(index);
    }

    public Group getGroupById(final String id) {
        return groupMap.get(id);
    }

    @Override
    public void saveData() {
        try (final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data_group.tmp"))) {
            final File f = new File("data_group.tmp");
            if (f.isFile())
                f.delete();
            oos.writeObject(getGroupList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadData() {
        try (final ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data_group.tmp"))) {
            final List<Group> tasks = (List<Group>) ois.readObject();
            for (Group group : tasks)
                groupMap.put(group.getId(), group);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
