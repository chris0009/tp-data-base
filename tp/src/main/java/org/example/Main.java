package org.example;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        /* Load JDBC Driver. */
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String pass = "chiheb";

        try (Connection connexion = DriverManager.getConnection(url, user, pass)) {
            System.out.println("Bdd Connected");




                    DAO<Dept> departmentDao = DAOFactory.getDeptDAO(connexion);
            Dept dept20 = departmentDao.find(20);
            System.out.println(dept20); // Assuming toString() method is implemented in Dept.java

            DAO<Emp> employeeDao = DAOFactory.getEmpDAO(connexion);
            Emp emp7369 = employeeDao.find(7369);
            System.out.println(emp7369); // Assuming toString() method is implemented in Emp.java



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







}
