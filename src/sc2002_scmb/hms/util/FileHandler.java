package sc2002_scmb.hms.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.List;
import java.util.ArrayList;

public class FileHandler {

    public static List<String> loadTextFile(String filePath) throws IOException {

        List<String> content = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        }
        return content;
    }

    public static void saveTextFile(String filePath, List<String> content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine(); // Add a new line after each line written
            }
        }
    }

    public static String[] splitLine(String line, String delimitter) {
        if (line.contains(delimitter))
            return line.split(delimitter);
        else
            return new String[] { line };
    }
}
