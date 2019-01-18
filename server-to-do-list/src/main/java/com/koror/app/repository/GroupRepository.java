package com.koror.app.repository;

import com.koror.app.api.repository.IGroupRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IGroupRepository {

    protected final Connection connection = DatabaseConnection.getConnection();

    public void add(Group group) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("insert into `group` (`ID`, `NAME`, `CREATOR`) values(?,?,?)");
            stmt.setString(1, group.getId());
            stmt.setString(2, group.getName());
            stmt.setString(3, group.getCreator());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from `group` where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group getById(String id) {
        final Group group = new Group();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from `group` where `ID` = ?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                group.setId(result.getString(1));
                group.setName(result.getString(2));
                group.setCreator(result.getString(3));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public List<Group> getList() {
        final List<Group> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from `group`");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final Group group = new Group();
                group.setId(result.getString(1));
                group.setName(result.getString(2));
                group.setCreator(result.getString(3));
                list.add(group);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final Group session) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("update `group` set `NAME` = ?, `CREATOR` = ? where `ID` = ?");
            stmt.setString(1, session.getName());
            stmt.setString(2, session.getCreator());
            stmt.setString(3, session.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public Group getGroupByIndex(final Integer index) {
//        return getList().get(index);
//    }

}
