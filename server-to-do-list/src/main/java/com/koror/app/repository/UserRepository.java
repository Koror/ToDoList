package com.koror.app.repository;

import com.koror.app.api.repository.IUserRepository;
import com.koror.app.database.DatabaseConnection;
import com.koror.app.entity.User;
import com.koror.app.enumerated.Access;
import com.koror.app.util.Hash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {

    private Connection connection = DatabaseConnection.getConnection();

    @Override
    public void add(User user) {
        try {
            user.setHashPassword(user.getPassword());
            final PreparedStatement stmt = connection.prepareStatement("insert into user (`ID`, `LOGIN`, `PASSWORD`, `NAME`, `EMAIL`, `ACCESS`) values(?,?,?,?,?,?)");
            stmt.setString(1, user.getId());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getAccess().toString());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            final PreparedStatement stmt = connection.prepareStatement("delete from user where `ID` = ?");
            stmt.setString(1, id);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getById(String id) {
        final User user = new User();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from user where `ID` = ?");
            stmt.setString(1, id);
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                user.setId(result.getString(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setName(result.getString(4));
                user.setEmail(result.getString(5));
                user.setAccess(Access.valueOf(result.getString(6)));
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getList() {
        final List<User> list = new ArrayList<>();
        try {
            final PreparedStatement stmt = connection.prepareStatement("select * from user");
            final ResultSet result = stmt.executeQuery();
            while (result.next()) {
                final User user = new User();
                user.setId(result.getString(1));
                user.setLogin(result.getString(2));
                user.setPassword(result.getString(3));
                user.setName(result.getString(4));
                user.setEmail(result.getString(5));
                user.setAccess(Access.valueOf(result.getString(6)));
                list.add(user);
            }
            stmt.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(final User user) {
        try {
            user.setHashPassword(user.getPassword());
            final PreparedStatement stmt = connection.prepareStatement("update user set `LOGIN` = ?, `PASSWORD` = ?, `NAME` = ?, `EMAIL` = ?, `ACCESS` = ? where `ID` = ?");
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getAccess().toString());
            stmt.setString(6, user.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getByLogin(final String login) {
        for (User user : getList()) {
            if (login.equals(user.getLogin()))
                return user;
        }
        return null;
    }

    @Override
    public User login(String login, String password) {
        User user = null;
        password = Hash.getHashString(password);
        for (User userTemp : getList()) {
            if ((login.equals(userTemp.getLogin())) && (password.equals(userTemp.getPassword()))) {
                user = userTemp;
            }
        }
        return user;
    }

}
