package com.DBrepository;

import com.DBconfig.DBservice;
import com.medical.Drug;
import com.patient.Adult;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DrugRepository {

    public void insertDrug(String name, int dose) {
        String insertSql = "INSERT INTO drugs (name, dose) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(insertSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, dose);
            preparedStatement.executeUpdate();
            System.out.println("1 row inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Drug getDrugById(int id) {
        String selectSql = "SELECT * FROM drugs WHERE drug_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDrug(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateDrugDose(int dose, int id) {
        String updateNameSql = "UPDATE drugs SET dose=? WHERE drug_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(updateNameSql);
            preparedStatement.setInt(1, dose);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("1 row updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Drug mapToDrug(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Drug(resultSet.getString(2), resultSet.getInt(3));
        }
        return null;
    }

    public void listAllDrugs() {
        String selectSql = "SELECT * FROM drugs";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Name:" + resultSet.getString(2));
                System.out.println("Dose:" + resultSet.getInt(3));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDrugById( int id) {
        String deleteSql = "DELETE FROM drugs WHERE drug_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(deleteSql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("1 row deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
