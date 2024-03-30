package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDAO extends DAO<Emp> {
    public EmpDAO(Connection connect) {
        super(connect);
    }

    @Override
    public Emp find(int id) {
        Emp employee = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String query = "SELECT * FROM emp WHERE empno = ?";
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                employee = new Emp();
                employee.setEmpNo(resultSet.getInt("empno"));
                employee.setEname(resultSet.getString("ename"));
                employee.setEfirst(resultSet.getString("efirst"));
                // Set other attributes...

                // Set manager recursively
                int mgrId = resultSet.getInt("mgr");
                if (mgrId != 0) {
                    EmpDAO empDAO = new EmpDAO(connect);
                    employee.setMgr(empDAO.find(mgrId));
                }
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

        return employee;
    }

    @Override
    public boolean create(Emp object) {
        // Implementation not provided for brevity
        return false;
    }

    @Override
    public boolean update(Emp object) {
        // Implementation not provided for brevity
        return false;
    }

    @Override
    public boolean delete(Emp object) {
        // Implementation not provided for brevity
        return false;
    }
}
