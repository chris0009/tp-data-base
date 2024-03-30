package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO<Dept> {
    public DeptDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Dept find(int id) {
        Dept department = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM dept WHERE deptno = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                department = new Dept();
                department.setDeptNo(resultSet.getInt("deptno"));
                department.setDname(resultSet.getString("dname"));
                department.setLoc(resultSet.getString("loc"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return department;
    }

    @Override
    public boolean create(Dept object) {
        // Implementation not provided for brevity
        return false;
    }

    @Override
    public boolean update(Dept object) {
        // Implementation not provided for brevity
        return false;
    }

    @Override
    public boolean delete(Dept object) {
        // Implementation not provided for brevity
        return false;
    }
}
