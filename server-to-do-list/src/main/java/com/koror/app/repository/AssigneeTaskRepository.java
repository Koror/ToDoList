package com.koror.app.repository;

import com.koror.app.api.repository.IAssigneeTaskRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.AssigneeTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssigneeTaskRepository extends AbstractRepository<AssigneeTask> implements IAssigneeTaskRepository {

    protected final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void add(AssigneeTask assigneeTask) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("insert into assigneetask (`ID`, `USERID`, `TASKID`) values(?,?,?)");
            stmt.setString(1, assigneeTask.getId());
            stmt.setString(2, assigneeTask.getUserId());
            stmt.setString(3, assigneeTask.getTaskId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from assigneetask where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public AssigneeTask getById(String id) {
        final AssigneeTask assigneeTask = new AssigneeTask();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from assigneetask where `ID` = ?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                assigneeTask.setId(result.getString(1));
                assigneeTask.setUserId(result.getString(2));
                assigneeTask.setTaskId(result.getString(3));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assigneeTask;
    }

    public List<AssigneeTask> getList() {
        final List<AssigneeTask> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from assigneetask");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final AssigneeTask assigneeTask = new AssigneeTask();
                assigneeTask.setId(result.getString(1));
                assigneeTask.setUserId(result.getString(2));
                assigneeTask.setTaskId(result.getString(3));
                list.add(assigneeTask);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final AssigneeTask assigneeTask) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("update assigneetask set `USERID` = ? 'TASKID' id = ? where `ID` = ?");
            stmt.setString(1, assigneeTask.getUserId());
            stmt.setString(2, assigneeTask.getTaskId());
            stmt.setString(3, assigneeTask.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAssigneeByParam(String userId, String taskId) {
        for (AssigneeTask assigneeTask : getList()) {
            if (userId.equals(assigneeTask.getUserId()) && taskId.equals(assigneeTask.getTaskId()))
                delete(assigneeTask.getId());
        }
    }

    @Override
    public AssigneeTask getAssigneeByUserId(String userId) {
        for (AssigneeTask assigneeTask : getList()) {
            if (assigneeTask.getUserId().equals(userId))
                return assigneeTask;
        }
        return null;
    }

}
