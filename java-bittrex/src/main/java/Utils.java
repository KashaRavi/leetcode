import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Utils {
    static  final String WINDOWS_DATA_LOCATION = "D:\\rkasha\\My Projects\\leetcode\\java-bittrex\\data\\%s";
    static  final String MAC_DATA_LOCATION = "/Users/rkasha/workspace/leetcode/java-bittrex/data/%s";


    public static String prettyPrintJsonStr(String jsonStr) {
        JsonParser jp = new JsonParser();
        JsonElement je;
        je = jp.parse(jsonStr);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    public static void writeToFile(String content, String fileName) {
        String fullPath = String.format("/Users/rkasha/workspace/automation/bittrex/%s.json", fileName);

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(fullPath);
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void loadCurrencies(String textFile, List<String> currencies) {
        BufferedReader b = getBufferedReader(textFile);
        try {
            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                String currency = readLine;
                currencies.add(currency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadCustomProperties(String textFile, Map<String, String> customProperties) {
        BufferedReader b = getBufferedReader(textFile);

        try {
            String readLine = "";

            while ((readLine = b.readLine()) != null) {
                String[] pairs = readLine.split("=");
                customProperties.put(pairs[0], pairs[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static BufferedReader getBufferedReader(String textFile) {
        String filename = String.format(WINDOWS_DATA_LOCATION, textFile);
        File f = null;
        BufferedReader b = null;
        try {
            f = new File(filename);
            b = new BufferedReader(new FileReader(f));

        } catch (FileNotFoundException fileEx) {
            filename = String.format(MAC_DATA_LOCATION, textFile);
            try {
                f = new File(filename);
                b = new BufferedReader(new FileReader(f));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b;
    }
}
