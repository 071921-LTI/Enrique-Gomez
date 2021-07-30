package com.lntinfotech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    private static Connection connection;

    public static Connection getConnection()  throws SQLException {
        String url = System.getenv("PROJECT0_POSTGRES_URL");
        String username = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }

        return connection;
    }
}
