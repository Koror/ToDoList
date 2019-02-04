package com.koror.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    public static String JDBC_DRIVER;

    public static String URL;

    public static String USER;

    public static String PASSWORD;

    public static String HIBERNATE_DIALECT;

    public static String HBM2DDL_AUTO;

    public static String HIBERNATE_SHOW_SQL;

    public static String SALT = "SuGar";

    public static int CYCLE = 55;

    static {
        InputStream fis;
        Properties property = new Properties();
        fis = AppConfig.class.getClassLoader().getResourceAsStream("config.properties");
        try {
            property.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JDBC_DRIVER = property.getProperty("db.driver");
        URL = property.getProperty("db.url");
        USER = property.getProperty("db.user");
        PASSWORD = property.getProperty("db.password");
        HIBERNATE_DIALECT = property.getProperty("h.dialect");
        HBM2DDL_AUTO = property.getProperty("h.auto");
        HIBERNATE_SHOW_SQL = property.getProperty("h.show_sql");
    }

}
