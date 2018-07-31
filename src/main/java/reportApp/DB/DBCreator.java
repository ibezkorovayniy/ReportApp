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

    public void createDB(String company, Connection connection) {
        try(Statement statement = connection.createStatement();) {
            statement.execute("DROP SCHEMA IF EXISTS " + company +  " CASCADE");
            statement.execute("CREATE SCHEMA IF NOT EXISTS " + company);
            statement.execute("SET SCHEMA " + company);
            executeScript("/SQL/Staff.sql", statement);
            executeScript("/SQL/Departments.sql", statement);
            executeScript("/SQL/Employees.sql", statement);
            logger.info("Tables for {} created", company);
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
