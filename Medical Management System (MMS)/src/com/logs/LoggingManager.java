package com.logs;

import com.data.DoctorDataManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LoggingManager {
    private String loggingFile = "app_loggings.txt";
    private static LoggingManager single_instance = null;

    private LoggingManager() {}

    public static LoggingManager LoggingManager()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new LoggingManager();
        }
        return single_instance;
    }

    public String createLog(String action, String timestamp, String result, String message) {
        return action + " " + timestamp + " " + result + " " + message + '\n';
    }

    public void write(String log) {
        //  get the path to the current working directory
        File currentDir = new File("").getAbsoluteFile();
        // set the path to the csv file in the current directory
        loggingFile = currentDir + "/Medical Management System (MMS)/src/com/logs/" + loggingFile;

        try (BufferedWriter buffer = new BufferedWriter(new
                FileWriter(loggingFile))) {
            buffer.write(log);
        }
        catch (IOException e) {
            System.out.println(String.format("Something occurred while writing the file ", loggingFile));
            e.printStackTrace();
        }
    }


}
