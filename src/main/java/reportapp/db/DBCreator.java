package reportapp.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreator {

    private Logger logger = LoggerFactory.getLogger(DBCreator.class);

    public void createDB(String company, Connection connection) {
        try(Statement stmt = connection.createStatement();) {
            stmt.execute("DROP SCHEMA IF EXISTS " + company +  " CASCADE");
            stmt.execute("CREATE SCHEMA IF NOT EXISTS " + company);
            stmt.execute("SET SCHEMA " + company);
            executeScript("/SQL/Staff.sql", stmt);
            executeScript("/SQL/Departments.sql", stmt);
            executeScript("/SQL/Employees.sql", stmt);
            logger.info("Tables for {} created", company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void executeScript(String scriptPath, Statement stmt) {
        InputStream in = getClass().getResourceAsStream(scriptPath);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            stmt.execute(sb.toString());
            logger.info("Script {} executed", scriptPath);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
