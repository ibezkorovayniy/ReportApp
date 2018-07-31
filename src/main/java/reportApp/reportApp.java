package reportApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reportApp.DB.DBConnect;
import reportApp.DB.DBCreator;
import reportApp.DB.DBPopulate;

import java.sql.Connection;
import java.sql.SQLException;

public class reportApp {

    public static void main(String[] args) {

        Connection connection = DBConnect.getConnection();
        DBCreator dbc = new DBCreator();
        DBPopulate dbp = new DBPopulate();
        dbc.createDB("COMPANY_A", connection);
        dbc.createDB("COMPANY_B", connection);
        dbp.populateCompany("COMPANY_A", connection);
        dbp.populateCompany("COMPANY_B", connection);


    }
}
