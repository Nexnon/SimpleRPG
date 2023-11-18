package ru.vsu.cs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static Connection connector() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/simplerpg";
        String username = "postgres";
        String password = "1234";
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connect!");
        return connection;
    }
}
