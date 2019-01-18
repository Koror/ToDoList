package com.koror.app.repository;

import com.koror.app.api.repository.ITaskRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.Task;
import com.koror.app.enumerated.Priority;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements ITaskRepository {

    protected final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void add(Task task) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("insert into task (`ID`, `NAME`, `PRIORITY`, `COMPLETE`, `CREATOR`, `GROUPID`) values(?,?,?,?,?,?)");
            stmt.setString(1, task.getId());
            stmt.setString(2, task.getName());
            stmt.setString(3, task.getPriority().toString());
            stmt.setBoolean(4, task.isComplete());
            stmt.setString(5, task.getCreator());
            stmt.setString(6, task.getGroupId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from task where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task getById(String id) {
        final Task task = new Task();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from task where `ID`=?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                task.setId(result.getString(1));
                task.setName(result.getString(2));
                task.setPriority(Priority.valueOf(result.getString(3)));
                task.setComplete(result.getBoolean(4));
                task.setCreator(result.getString(5));
                task.setGroupId(result.getString(6));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public List<Task> getList() {
        final List<Task> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from task");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final Task task = new Task();
                task.setId(result.getString(1));
                task.setName(result.getString(2));
                task.setPriority(Priority.valueOf(result.getString(3)));
                task.setComplete(result.getBoolean(4));
                task.setCreator(result.getString(5));
                task.setGroupId(result.getString(6));
                list.add(task);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final Task task) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("update task set `NAME` = ?, `PRIORITY` = ?, `COMPLETE` = ?, `CREATOR` = ?, `GROUPID` = ? where ID = ?");
            stmt.setString(1, task.getName());
            stmt.setString(2, task.getPriority().toString());
            stmt.setBoolean(3, task.isComplete());
            stmt.setString(4, task.getCreator());
            stmt.setString(5, task.getGroupId());
            stmt.setString(6, task.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void completeTask(final Task task) {
        task.setComplete(true);
        update(task);
    }

    @Override
    public void clearTask(final List<Task> taskList) {
        for (Task task : taskList) {
            if (task.isComplete())
                delete(task.getId());
        }
    }

    @Override
    public void setGroupId(final Task task, final String idGroup) {
        task.setGroupId(idGroup);
        update(task);
    }

    @Override
    public Task getTaskByIndex(Integer index) {
        return getList().get(index);
    }

}

