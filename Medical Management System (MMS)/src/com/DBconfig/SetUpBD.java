package com.DBconfig;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpBD {

    public void createTable(String table, String values) {
        String createTableSql = "CREATE TABLE IF NOT EXISTS " + table + values;

        try {
            Statement stmt = DBservice.getStmt();
            stmt.execute(createTableSql);
            System.out.println("table " + table + " created!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropTable(String table) {
        String dropTableSql = "DROP TABLE IF EXISTS " + table;

        try {
            Statement stmt = DBservice.getStmt();
            stmt.execute(dropTableSql);
            System.out.println("table " + table + " dropped!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}