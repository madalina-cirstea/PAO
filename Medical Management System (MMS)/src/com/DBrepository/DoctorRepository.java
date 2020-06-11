package com.DBrepository;

import com.app.Hospital;
import com.medical.Doctor;
import com.DBconfig.DBservice;

import java.sql.*;

public class DoctorRepository {
    public void insertDoctor(Doctor doctor, String hospitalName) {
        String insertSql = "INSERT INTO doctors (specialization, CNP, name, age, sex, hospital_name) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(insertSql);
            preparedStatement.setString(1, doctor.getSpecialization());
            preparedStatement.setString(2, doctor.getCNP());
            preparedStatement.setString(3, doctor.getName());
            preparedStatement.setInt(4, doctor.getAge());
            preparedStatement.setString(5, doctor.getSex());
            preparedStatement.setString(6, hospitalName);
            preparedStatement.executeUpdate();
            System.out.println("1 row inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorById(int id) {
        String selectSql = "SELECT * FROM doctors WHERE doctor_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDoctor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Doctor getGeneralPractitionerById(int id) {
        String selectSql = "SELECT * FROM doctors WHERE doctor_id=? and specialization=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "general practitioner");
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDoctor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getDoctorId(String CNP) {
        String selectSql = "SELECT doctor_id FROM doctors WHERE CNP=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setString(1, CNP);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private Doctor mapToDoctor(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
             return new Doctor(resultSet.getString(2), resultSet.getString(4), resultSet.getString(3), resultSet.getInt(5), resultSet.getString(6));
        }
        return null;
    }

    public void listAllDoctors() {
        String selectSql = "SELECT * FROM doctors";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("Specialization:" + resultSet.getString(2));
                System.out.println("CNP:" + resultSet.getString(3));
                System.out.println("Name:" + resultSet.getString(4));
                System.out.println("Age:" + resultSet.getInt(5));
                System.out.println("Sex:" + resultSet.getString(6));
                System.out.println("Hospital:" + resultSet.getString(7));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllDoctors(String specialization) {
        String selectSql = "SELECT * FROM doctors WHERE specialization=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setString(1, specialization);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("Specialization:" + resultSet.getString(2));
                System.out.println("CNP:" + resultSet.getString(3));
                System.out.println("Name:" + resultSet.getString(4));
                System.out.println("Age:" + resultSet.getInt(5));
                System.out.println("Sex:" + resultSet.getString(6));
                System.out.println("Hospital:" + resultSet.getString(7));
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctorName(String name, int id) {
        String updateNameSql = "UPDATE doctors SET name=? WHERE doctor_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("1 row updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctorById(int id) {
        String deleteSql = "DELETE FROM doctors WHERE doctor_id=?";

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
