package reportApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class reportApp {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(reportApp.class);

        Connection connection = DBConnect.getConnection();
        logger.info("Connection to database established");

    }
}
