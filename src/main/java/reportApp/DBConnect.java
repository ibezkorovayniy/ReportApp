package reportApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnect {
    private static ResourceBundle rb = ResourceBundle.getBundle("db");
    private static final String URL = rb.getString("URL");
    private static final String USER = rb.getString("username");
    private static final String PASS = rb.getString("password");

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver for database not found");
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
