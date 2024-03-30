package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "chiheb";

    private static final int INITIAL_POOL_SIZE = 10;
    private static final List<Connection> connectionPool = new ArrayList<>();

    static {
        initializeConnectionPool();
    }

    private static void initializeConnectionPool() {
        try {
            for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connectionPool.add(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() {
        if (connectionPool.isEmpty()) {
            throw new RuntimeException("Connection pool is empty");
        }
        Connection connection = connectionPool.remove(connectionPool.size() - 1);
        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) {
        connectionPool.add(connection);
    }
}

