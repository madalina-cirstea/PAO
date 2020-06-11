package com.DBconfig;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// singleton class for database connection
public class DBservice {
    private DBservice() {}

    public static PreparedStatement getStatement(String sql) {
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            return stm;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Statement getStmt() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        Statement stmt = null;
        try {
            stmt = databaseConnection.createStatement();
            return stmt;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}