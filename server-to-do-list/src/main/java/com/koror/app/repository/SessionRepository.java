package com.koror.app.repository;

import com.koror.app.api.repository.ISessionRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SessionRepository implements ISessionRepository {

    protected final Connection connection = DatabaseConnection.getConnection();

    @Override
    public void add(Session session) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("insert into session (`ID`, `USERID`, `SIGNATURE`) values(?,?,?)");
            stmt.setString(1, session.getId());
            stmt.setString(2, session.getUserId());
            stmt.setString(3, session.getSignature());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from session where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Session getById(String id) {
        final Session session = new Session();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from session where `ID` = ?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                session.setId(result.getString(1));
                session.setUserId(result.getString(2));
                session.setSignature(result.getString(3));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    public List<Session> getList() {
        final List<Session> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from session");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final Session session = new Session();
                session.setId(result.getString(1));
                session.setUserId(result.getString(2));
                session.setSignature(result.getString(3));
                list.add(session);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final Session session) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("update session set `USERID` = ?, `SIGNATURE` = ? where `ID` = ?");
            stmt.setString(1, session.getUserId());
            stmt.setString(2, session.getSignature());
            stmt.setString(3, session.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
