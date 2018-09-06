package main.java.Utilities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {
    public static void main(String[] args) {
//      prepareZerodhaUrl();
//        printFormats();

        LocalDateTime toTradingDate = LocalDateTime.of(2018, 6, 14, 9, 15, 25);
        String str = toTradingDate.toLocalDate().toString();
        System.out.println(str);
    }

    public static void prepareZerodhaUrl() {
        String exchangeToken = "2672641";
        String timeFrame = "minute";
        String publicToken = "xxxx";
        String userId = "yyyy";
        String apiKey = "kitefront";
        String accessToken = "zzzzz";
        String from = "2018-06-10";
        String to = "2018-06-15";

        String stockChartDataUrl = String.format(
                "https://kitecharts.zerodha.com/api/chart/%s/%s?public_token=%s&user_id=%s&api_key=%s&access_token=%s&from=%s&to=%s",
                exchangeToken, timeFrame, publicToken, userId, apiKey, accessToken, from, to);
        System.out.println(stockChartDataUrl);
    }



    public static void printFormats() {
        List<List<String>> logData = new ArrayList<List<String>>() {
            {
                add(Arrays.asList("2018-06-16T09:15:34+05:30", "12345.65","987.567", "BUY", "Yes"));
                add(Arrays.asList("2018-06-16T09:16:34+05:30", "945.35","1187.45207", "SELL", "Yes"));
                add(Arrays.asList("2018-06-16T09:17:34+05:30", "989.90","887.75607", "NONE", "No"));
            }
        };
        logData.forEach(row -> {
            String message = String.format("%s\t%8s\t%-8s\t%-4s\t%s", row.get(0), row.get(1),
                    row.get(2), row.get(3), row.get(4));
            System.out.println(message);
        });
    }
}
