package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    /**
     * Метод принимает название интерфейса и ищет в файле properties
     * название имплементирующего класса
     *
     * @param interfaceName - название интерфейса
     * @return название имплементирующего класса
     */
    public static String getImplementationClass(String interfaceName) {
        String csvFilePath = "src\\main\\resources\\properties";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFilePath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split("=");
                if (values[0].equals(interfaceName))
                    return values[1];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
