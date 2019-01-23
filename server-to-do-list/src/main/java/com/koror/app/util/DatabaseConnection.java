package com.koror.app.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static void setConnection() {
        try {
            AppConfig.init();
            Class.forName(AppConfig.JDBC_DRIVER);
            connection = DriverManager.getConnection(AppConfig.URL, AppConfig.USER, AppConfig.PASSWORD);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
