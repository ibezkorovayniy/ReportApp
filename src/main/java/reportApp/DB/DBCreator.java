package reportApp.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreator {

    private Logger logger = LoggerFactory.getLogger(DBCreator.class);

    public void createDB(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA IF EXISTS COMPANY_A");
            statement.execute("DROP SCHEMA IF EXISTS COMPANY_B");

            statement.execute("CREATE SCHEMA IF NOT EXISTS COMPANY_A");
            statement.execute("CREATE SCHEMA IF NOT EXISTS COMPANY_B");

            statement.execute("SET SCHEMA COMPANY_A");
            executeScript("/SQL/Staff.sql", statement);
            executeScript("/SQL/Departments.sql", statement);
            executeScript("/SQL/Employees.sql", statement);
            logger.info("Tables for Company_A created");

            statement.execute("SET SCHEMA COMPANY_B");
            executeScript("/SQL/Staff.sql", statement);
            executeScript("/SQL/Departments.sql", statement);
            executeScript("/SQL/Employees.sql", statement);
            logger.info("Tables for Company_B created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean executeScript(String scriptPath, Statement stmt) {
        boolean isExecuted = false;
        InputStream in = getClass().getResourceAsStream(scriptPath);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            stmt.execute(sb.toString());
            isExecuted = true;
            logger.info("Script {} executed", scriptPath);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
        return isExecuted;
    }
}
