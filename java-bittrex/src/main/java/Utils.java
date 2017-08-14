import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Utils {

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

        try (Scanner scan = new Scanner(Bittrex.class.getResourceAsStream(textFile))) {
            while(scan.hasNextLine()){
                String currency = scan.nextLine();
                currencies.add(currency);
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {

            System.err.println("Currencies file not found or corrupted.");
        }
    }
}
