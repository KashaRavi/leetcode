package hiring.expedia.question1;

/**
 * Created by rkasha on 1/22/19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TestClass {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String inputString = input.readLine();
        double s = Double.parseDouble(inputString);
        //a,b,c,d
        Map<Long, Integer> countMap = new HashMap<>();
        for (int a = 0; a <= 1000; a++) {
            for (int b = 0; b <= 1000; b++) {
                long key = a + b * b;
                if (countMap.containsKey(key)) {
                    countMap.put(key, countMap.get(key) + 1);
                } else {
                    countMap.put(key, 1);
                }
            }
        }

        long count = 0;
        for (int c = 0; c <= 1000; c++) {
            for (int d = 0; d <= 1000; d++) {
                long val = c * c * c + d * d * d * d;
                double dKey = s - val;
                if (dKey >= 0 && dKey <= 2 * 1000 * 1000) {
                    Integer pairCount = countMap.get((long)dKey);
                    count += (pairCount == null) ? 0 : pairCount;
                }
            }
        }
        System.out.println(count);
    }
}
