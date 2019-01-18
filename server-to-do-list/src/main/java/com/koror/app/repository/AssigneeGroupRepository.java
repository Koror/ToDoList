package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeGroupRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.AssigneeGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssigneeGroupRepository implements IAssigneeGroupRepository {

    protected final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void add(AssigneeGroup assigneeGroup) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("insert into assigneegroup (`ID`, `USERID`, `GROUPID`) values(?,?,?)");
            stmt.setString(1, assigneeGroup.getId());
            stmt.setString(2, assigneeGroup.getUserId());
            stmt.setString(3, assigneeGroup.getGroupId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from assigneegroup where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AssigneeGroup getById(String id) {
        final AssigneeGroup assigneeGroup = new AssigneeGroup();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from assigneegroup where `ID`=?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                assigneeGroup.setId(result.getString(1));
                assigneeGroup.setUserId(result.getString(2));
                assigneeGroup.setGroupId(result.getString(3));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assigneeGroup;
    }

    public List<AssigneeGroup> getList() {
        final List<AssigneeGroup> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from assigneegroup");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final AssigneeGroup assigneeGroup = new AssigneeGroup();
                assigneeGroup.setId(result.getString(1));
                assigneeGroup.setUserId(result.getString(2));
                assigneeGroup.setGroupId(result.getString(3));
                list.add(assigneeGroup);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final AssigneeGroup assigneeGroup) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("update assigneegroup set `USERID` = ? 'GROUPID' id = ? `ID` = ?");
            stmt.setString(1, assigneeGroup.getUserId());
            stmt.setString(2, assigneeGroup.getGroupId());
            stmt.setString(3, assigneeGroup.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAssigneeByParam(String userId, String groupId) {
        for (AssigneeGroup assigneeGroup : getList()) {
            if (userId.equals(assigneeGroup.getUserId()) && groupId.equals(assigneeGroup.getGroupId()))
                delete(assigneeGroup.getId());
        }
    }

    @Override
    public AssigneeGroup getAssigneeByUserId(String userId) {
        for (AssigneeGroup assigneeGroup : getList()) {
            if (assigneeGroup.getUserId().equals(userId))
                return assigneeGroup;
        }
        return null;
    }

}
