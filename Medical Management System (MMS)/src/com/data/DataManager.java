package com.data;

import com.app.Hospital;
import com.patient.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface DataManager <T>{
     public List<T> load(String fileName);
     public String createLine(T ob, List<String> header);

     public default List<String> parseLine(String line) {
         String csvDelimiter = ",";
         return Arrays.asList(line.split(csvDelimiter));
     }

     public default void writeLine(BufferedWriter buffer, List<String> header, T ob) throws IOException {
          buffer.write(new String(createLine(ob, header)));
          buffer.write('\n');
     }

     public default void writeLine(BufferedWriter buffer, String headerLine) throws IOException {
          buffer.write(headerLine);
          buffer.write('\n');
     }

     public default List<List<String>> read(String fileName) {
        //  get the path to the current working directory
        File currentDir = new File("").getAbsoluteFile();
        // set the path to the csv file in the current directory
        fileName = currentDir + "/Medical Management System (MMS)/src/com/data/" + fileName;

        List<List<String>> lines = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new
                FileReader(fileName))) {
            String line = buffer.readLine();
            while (line != null) {
                lines.add(parseLine(line));
                line = buffer.readLine();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("The file does not exist!");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println(String.format("Something occurred while reading the file ", fileName));
            e.printStackTrace();
        }

        return lines;
    }

     public default void write(String fileName, List<String> header, String headerLine, List<T> objs) {
          //  get the path to the current working directory
          File currentDir = new File("").getAbsoluteFile();
          // set the path to the csv file in the current directory
          fileName = currentDir + "/Medical Management System (MMS)/src/com/data/" + fileName;

          try (BufferedWriter buffer = new BufferedWriter(new
                  FileWriter(fileName))) {
               writeLine(buffer, headerLine);
               for(T obj:objs) {
                    writeLine(buffer, header, obj);
               }
          }
          catch (IOException e) {
               System.out.println(String.format("Something occurred while writing the file ", fileName));
               e.printStackTrace();
          }
     }

}
