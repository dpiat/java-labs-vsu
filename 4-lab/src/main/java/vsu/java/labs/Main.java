package vsu.java.labs;

import com.opencsv.CSVReader;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String csvFilePath = "src\\main\\resources\\foreign_names.csv";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));

            List<Person> personList = new ArrayList<>();
            String line = bufferedReader.readLine(); // skip first row
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(";");
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
