package com.DBconfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mms?" + "autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "bazededate";

    private static Connection databaseConnection;

    private DatabaseConfiguration() {

    }

    public static Connection getDatabaseConnection() {
        try {
            if (databaseConnection ==  null || databaseConnection.isClosed()) {
                databaseConnection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return databaseConnection;
    }

    public static void closeDatabaseConnection() {
        try {
            if (databaseConnection != null && !databaseConnection.isClosed()) {
                databaseConnection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}