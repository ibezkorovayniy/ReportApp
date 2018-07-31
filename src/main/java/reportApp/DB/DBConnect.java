package reportApp.DB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reportApp.reportApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnect {
    private static ResourceBundle rb = ResourceBundle.getBundle("db");
    private static final String URL = rb.getString("URL");
    private static final String USER = rb.getString("username");
    private static final String PASS = rb.getString("password");
    private static final String DRIVER = rb.getString("driver");
    private static Logger logger = LoggerFactory.getLogger(DBConnect.class);

    static {
        try {
            Class.forName(DRIVER);
            logger.info("Driver for H2 Database loaded");
        } catch (ClassNotFoundException e) {
            logger.error("Driver for database not found");
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            logger.info("Connection to database established");
            return connection;
        } catch (SQLException e) {
            logger.error("Error connecting to the database");
            throw new RuntimeException(e);
        }
    }
}
