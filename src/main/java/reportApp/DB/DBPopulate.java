package reportApp.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DBPopulate {

    private Logger logger = LoggerFactory.getLogger(DBPopulate.class);

    public void populateCompany(String company, Connection connection) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SET SCHEMA " + company);
            populateStaff(stmt);
            logger.info("Table 'Staff' for {} populated", company);
            populateDepartments(stmt);
            logger.info("Table 'Departments' for {} populated", company);
            populateEmployees(stmt);
            logger.info("Table 'Employees' for {} populated", company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void populateStaff(Statement stmt) throws SQLException {
        for (int i = 1; i <= 1000; i++) {
            int age = ThreadLocalRandom.current().nextInt(20, 60 + 1);
            stmt.execute("INSERT INTO STAFF VALUES("
                    + i + ", 'employee" + i + "', " + age + ")"
            );
        }
    }

    private void populateDepartments(Statement stmt) throws SQLException {
        for (int i = 1; i <= 20; i++) {
            stmt.execute("INSERT INTO DEPARTMENTS VALUES("
                    + i + ", 'Department" + i + "', 'District" + i + "')"
            );
        }
    }

    private void populateEmployees(Statement stmt) throws SQLException {
        for(int i = 1; i <= 1000; i++) {
            int depId = ThreadLocalRandom.current().nextInt(1, 20 + 1);
            stmt.execute("INSERT INTO EMPLOYEES VALUES(" + i + ", " + depId + ")");
        }


        }
}


