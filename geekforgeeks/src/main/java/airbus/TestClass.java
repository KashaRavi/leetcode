package airbus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// for larger values it throws stackoverflow

/**
 * Created by rkasha on 3/2/19.
 */
public class TestClass {
    static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cache.put(2, 1);
        cache.put(1, 0);
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(findMinDays(n));
            System.out.println();
        }
    }

    /**
     * f()
     * @param n
     * @return
     */

    public static int findMinDays(int n) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        if ((n & n - 1) == 0) {
            double val3 = Math.log10(n) / Math.log10(2);
            cache.put(n, (int) val3);
            return cache.get(n);
        }

        int val = findMinDays(n - 1);
        if (n % 2 == 0) {
            int val2 = findMinDays(n / 2);
            val = Math.min(val, val2) + 1;
        } else {
            val = val + 1;
        }
        cache.put(n, val);
        return cache.get(n);
    }

}
