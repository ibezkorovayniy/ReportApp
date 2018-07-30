package reportApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reportApp.DB.DBConnect;
import reportApp.DB.DBCreator;

import java.sql.Connection;

public class reportApp {

    public static void main(String[] args) {

        Connection connection = DBConnect.getConnection();
        DBCreator dbc = new DBCreator();
        dbc.createDB(connection);
    }
}
