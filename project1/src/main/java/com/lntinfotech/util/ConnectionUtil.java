package com.lntinfotech.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = System.getenv("POSTGRES_URL");
        String user = System.getenv("POSTGRES_USER");
        String password = System.getenv("POSTGRES_PASSWORD");

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }

        return connection;
    }
}
