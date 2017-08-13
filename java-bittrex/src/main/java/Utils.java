import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
}
