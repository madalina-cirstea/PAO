package com.logs;

import com.data.DoctorDataManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        return "action: " + action + "     timestamp: " + timestamp + "     result:" + result + "     message: " + message + '\n';
    }

    public List<String> read(String fileName) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new
                FileReader(fileName))) {
            String line = buffer.readLine();
            while (line != null) {
                line += "\n";
                lines.add(line);
                line = buffer.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " does not exist!");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println(String.format("Something occurred while reading the file ", fileName));
            e.printStackTrace();
        }

        return lines;
    }

    public void write(String log) {
        //  get the path to the current working directory
        File currentDir = new File("").getAbsoluteFile();
        // set the path to the csv file in the current directory
        String file = currentDir + "/Medical Management System (MMS)/src/com/logs/" + loggingFile;
        List<String> lines = read(file);


        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(file))) {
            for (String line:lines)
                buffer.write(line);
            buffer.write(log);
        }
        catch (IOException e) {
            System.out.println(String.format("Something occurred while writing the file ", file));
            e.printStackTrace();
        }
    }
}
