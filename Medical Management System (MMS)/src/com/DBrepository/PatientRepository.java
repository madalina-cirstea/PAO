package com.DBrepository;

import com.medical.Doctor;
import com.patient.Minor;
import com.patient.Patient;
import com.patient.Senior;
import com.patient.Adult;
import com.DBconfig.DBservice;

import javax.print.Doc;
import java.sql.*;

public class PatientRepository {

    public void insertPatient(Patient patient) {
        String insertSql = "INSERT INTO patients (CNP, name, age, sex, parent_id, doctor_id, details) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            DoctorRepository DoctorRepo = new DoctorRepository();
            PreparedStatement preparedStatement = DBservice.getStatement(insertSql);
            preparedStatement.setString(1, patient.getCNP());
            preparedStatement.setString(2, patient.getName());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setString(4, patient.getSex());
            preparedStatement.setInt(6, DoctorRepo.getDoctorId(patient.getAssignedDoctorCNP()));

            if (patient.getAge() > 60) {
                preparedStatement.setNull(5, Types.INTEGER);
                preparedStatement.setFloat(7, ((Senior)patient).getPension());
            }
            else if (patient.getAge() < 18) {
                preparedStatement.setInt(5, getPatientId(((Minor)patient).getTutorCNP()));
                preparedStatement.setNull(7, Types.FLOAT);
            }
            else {
                preparedStatement.setNull(5, Types.INTEGER);
                preparedStatement.setFloat(7, ((Adult)patient).getMothlyIncome());
            }
            preparedStatement.executeUpdate();
            System.out.println("1 row inserted!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPatientId(String CNP) {
        String selectSql = "SELECT patient_id FROM patients WHERE CNP=?";

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

    public Patient getPatientById(int id) {
        String selectSql = "SELECT * FROM patients WHERE patient_id=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPatient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Patient getPatientByCNP(String CNP) {
        String selectSql = "SELECT * FROM patients WHERE CNP=?";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            preparedStatement.setString(1, CNP);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPatient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updatePatientName(String name, int id) {
        String updateNameSql = "UPDATE patients SET name=? WHERE patient_id=?";

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

    private Patient mapToPatient(ResultSet resultSet) throws SQLException {
        DoctorRepository DoctorRepo = new DoctorRepository();
        if (resultSet.next()){
            if (resultSet.getInt(4) < 18)
                return null; //new Minor(resultSet.getString(3), resultSet.getString(2), resultSet.getInt(4), resultSet.getString(5), null,.... resultSet.getString(6));
            else if (resultSet.getInt(4) > 60)
                return null;
            else {
                return new Adult(resultSet.getString(3), resultSet.getString(2), resultSet.getInt(4), resultSet.getString(5), resultSet.getFloat(8), DoctorRepo.getDoctorById(resultSet.getInt(7)));
            }
        }
        return null;
    }

    public void listAllPatients() {
        String selectSql = "SELECT * FROM patients";

        try {
            PreparedStatement preparedStatement = DBservice.getStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("CNP:" + resultSet.getString(2));
                System.out.println("Name:" + resultSet.getString(3));
                System.out.println("Age:" + resultSet.getInt(4));
                System.out.println("Sex:" + resultSet.getString(5));
                System.out.println("Parent ID:" + resultSet.getInt(6));
                System.out.println("Doctor Id:" + resultSet.getInt(7));
                if (resultSet.getInt(4) < 18)
                    System.out.println("Details: Minor");
                else if (resultSet.getInt(4) > 60)
                    System.out.println("Details: Retired with pension " + resultSet.getFloat(8));
                else System.out.println("Details: Adult with salary " + resultSet.getFloat(8));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatientById( int id) {
        String deleteSql = "DELETE FROM patients WHERE patient_id=?";

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
