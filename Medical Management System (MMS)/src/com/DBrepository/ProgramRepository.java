package com.DBrepository;

import com.DBconfig.DBservice;
import com.medical.Drug;
import com.medical.TimeSlot;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class ProgramRepository {

    public void insertProgram(int start, int end) {
        String insertSql = "INSERT INTO program (start_hour, end_hour) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(insertSql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            preparedStatement.executeUpdate();
            System.out.println("1 row inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TimeSlot getProgramById(int id) {
        String selectSql = "SELECT * FROM program WHERE program_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToProgram(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateProgramStart(int start, int id) {
        String updateNameSql = "UPDATE program SET start_hour=? WHERE program_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(updateNameSql);
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("1 row updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TimeSlot mapToProgram(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new TimeSlot(resultSet.getInt(2), resultSet.getInt(3));
        }
        return null;
    }

    public void listAllProgramHours() {
        String selectSql = "SELECT * FROM program";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getInt(1));
                System.out.println("Start:" + resultSet.getInt(2));
                System.out.println("End:" + resultSet.getInt(3));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProgramById( int id) {
        String deleteSql = "DELETE FROM program WHERE program_id=?";

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
