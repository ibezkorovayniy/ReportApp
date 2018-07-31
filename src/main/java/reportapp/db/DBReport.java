package reportapp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBReport {

    public void createReport(int ageFrom, int ageTo, String district, Connection connection) {

        String sqlQuery = "SELECT RESULT.DEPARTMENT as DEPARTMENTS, SUM(EMPLOYEES) AS EMPLOYEES\n" +
                "FROM\n" +
                "(SELECT DEPARTMENT, DEPARTMENT_ID, COUNT(*) AS EMPLOYEES\n" +
                "FROM COMPANY_A.DEPARTMENTS  AS A_DEP\n" +
                "JOIN\n" +
                "COMPANY_A.EMPLOYEES AS A_EMP ON A_EMP.DEPARTMENT_ID = A_DEP.ID\n" +
                "JOIN\n" +
                "COMPANY_A.STAFF AS A_ST ON A_EMP.EMPLOYEE_ID = A_ST.ID\n" +
                "WHERE (A_ST.AGE BETWEEN ? AND ?) AND (A_DEP.DISTRICT = ?)\n" +
                "GROUP BY DEPARTMENT\n" +
                "UNION\n" +
                "SELECT DEPARTMENT, DEPARTMENT_ID, COUNT(*) AS EMPLOYEES\n" +
                "FROM COMPANY_B.DEPARTMENTS  AS B_DEP\n" +
                "JOIN\n" +
                "COMPANY_B.EMPLOYEES AS B_EMP ON B_EMP.DEPARTMENT_ID = B_DEP.ID\n" +
                "JOIN\n" +
                "COMPANY_B.STAFF AS B_ST ON B_EMP.EMPLOYEE_ID = B_ST.ID\n" +
                "WHERE (B_ST.AGE BETWEEN ? AND ?) AND (B_DEP.DISTRICT = ?)\n" +
                "GROUP BY DEPARTMENT\n" +
                " ) AS RESULT\n" +
                "GROUP BY RESULT.DEPARTMENT\n" +
                "ORDER BY RESULT.DEPARTMENT_ID";


        try {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery);

            stmt.setInt(1, ageFrom);
            stmt.setInt(2, ageTo);
            stmt.setString(3, district);
            stmt.setInt(4, ageFrom);
            stmt.setInt(5, ageTo);
            stmt.setString(6, district);

            ResultSet rs = stmt.executeQuery();
            System.out.println("--------------------------------");
            System.out.printf("%10s %15s", "DEPARTMENT", "EMPLOYEES");
            System.out.println();
            System.out.println("--------------------------------");
            while (rs.next()) {
                    System.out.format("%10s %10d",
                            rs.getString("DEPARTMENTS"), rs.getInt("EMPLOYEES"));
                    System.out.println();
                }
                System.out.println("--------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
