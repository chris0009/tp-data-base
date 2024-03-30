package org.example;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class BoneCPConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "chiheb";

    private static BoneCP connectionPool;

    static {
        try {
            BoneCPConfig config = new BoneCPConfig();
            config.setJdbcUrl(URL);
            config.setUsername(USERNAME);
            config.setPassword(PASSWORD);
            config.setMinConnectionsPerPartition(5);
            config.setMaxConnectionsPerPartition(10);
            config.setPartitionCount(2);

            connectionPool = new BoneCP(config);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

    public static void closePool() {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }
}

