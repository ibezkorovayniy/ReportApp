package reportapp;

import reportapp.db.DBConnect;
import reportapp.db.DBCreator;
import reportapp.db.DBPopulate;
import reportapp.db.DBReport;

import java.sql.Connection;

public class ReportApp {

    public static void main(String[] args) {

        Connection connection = DBConnect.getConnection();
        DBCreator dbc = new DBCreator();
        DBPopulate dbp = new DBPopulate();
        DBReport dbr = new DBReport();

        dbc.createDB("COMPANY_A", connection);
        dbc.createDB("COMPANY_B", connection);
        dbp.populateCompany("COMPANY_A", connection);
        dbp.populateCompany("COMPANY_B", connection);
        dbr.createReport(20, 35, "District5", connection);



    }
}
