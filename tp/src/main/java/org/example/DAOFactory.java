package org.example;

import java.sql.Connection;

public class DAOFactory {
    public static DAO<Dept> getDeptDAO(Connection connect) {
        return new DeptDAO(connect);
    }

    public static DAO<Emp> getEmpDAO(Connection connect) {
        return new EmpDAO(connect);
    }
}

