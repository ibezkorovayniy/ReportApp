package reportapp;

import reportapp.db.DBConnect;
import reportapp.db.DBCreator;
import reportapp.db.DBPopulate;
import reportapp.db.DBReport;

import java.sql.Connection;
import java.util.Scanner;

public class ReportApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String company = "COMPANY_A";
        String company2 = "COMPANY_B";
        int ageFrom;
        int ageTo;
        String district;
        Connection connection = DBConnect.getConnection();
        DBCreator dbc = new DBCreator();
        DBPopulate dbp = new DBPopulate();
        DBReport dbr = new DBReport();

        System.out.println("Enter the following commands: ");
        System.out.println("1. createDB");
        System.out.println("2. populateDB");
        System.out.println("3. report");
        try {
            while (true) {
                if (dbc.isCreated()) {
                    System.out.println("Database is already created.");
                    System.out.println("Do you want to create DB again: y/n ?");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        while (!sc.nextLine().equalsIgnoreCase("createDB")) {
                            System.out.println("Wrong input! Enter first command");
                        }
                        dbc.createDB(company, connection);
                        dbc.createDB(company2, connection);
                    }
                } else {
                    System.out.println("Enter first command: ");
                    while (!sc.nextLine().equalsIgnoreCase("createDB")) {
                        System.out.println("Wrong input! Enter first command");
                    }
                    dbc.createDB(company, connection);
                    dbc.createDB(company2, connection);
                }

                if (dbp.isPopulated()) {
                    System.out.println("Database is already populated.");
                    System.out.println("Do you want to populate DB again: y/n ?");
                    if (sc.nextLine().equalsIgnoreCase("y")) {
                        while (!sc.nextLine().equalsIgnoreCase("populateDB")) {
                            System.out.println("Wrong input! Enter second command");
                        }
                        dbp.populateCompany(company, connection);
                        dbp.populateCompany(company2, connection);
                    }
                } else {
                    System.out.println("Enter next command: ");
                    while (!sc.nextLine().equalsIgnoreCase("populateDB")) {
                        System.out.println("Wrong input! Enter second command");
                    }
                    dbp.populateCompany(company, connection);
                    dbp.populateCompany(company2, connection);
                }

                System.out.println("Enter the last command: ");
                while (!sc.nextLine().equalsIgnoreCase("report")) {
                    System.out.println("Wrong input! Enter third command");
                }
                System.out.println("Please, provide age bounds: ");
                System.out.println("Age from: ");
                ageFrom = Integer.parseInt(sc.nextLine());
                System.out.println("Age to: ");
                ageTo = Integer.parseInt(sc.nextLine());
                System.out.println("Enter district: ");
                String input = sc.nextLine().toLowerCase();
                district = input.substring(0,1).toUpperCase() + input.substring(1).toLowerCase();
                dbr.createReport(ageFrom, ageTo, district, connection);
                System.out.println("Type 'exit' if you want to terminate:");
                if (sc.nextLine().equalsIgnoreCase("exit")) {
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
