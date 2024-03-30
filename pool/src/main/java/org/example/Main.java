package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
    /* exercie1     try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = ConnectionPool.getConnection();

        try {
            // Use the connection to execute SQL queries
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Emp");

            // Process the ResultSet
            while (resultSet.next()) {
                // Extract data from the ResultSet
                int empId = resultSet.getInt("empno");
                String empName = resultSet.getString("ename");

                // Print the retrieved data
                System.out.println("Employee ID: " + empId + ", Employee Name: " + empName);
            }

            // Close the ResultSet and Statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Release the connection back to the connection pool
            ConnectionPool.releaseConnection(connection);
        }
        */



        Connection connection = null;
        try {
            // Get a connection from the BoneCP connection pool
            connection = BoneCPConnectionPool.getConnection();

            // Use the connection to execute SQL queries
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM emp");

            // Process the ResultSet
            while (resultSet.next()) {
                // Extract data from the ResultSet
                int empId = resultSet.getInt("empno");
                String empName = resultSet.getString("ename");

                // Print the retrieved data
                System.out.println("Employee ID: " + empId + ", Employee Name: " + empName);
            }

            // Close the ResultSet and Statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}