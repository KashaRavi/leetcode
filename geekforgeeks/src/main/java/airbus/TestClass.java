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
    static Map<Integer, Integer> table = new HashMap<Integer, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        table.put(2, 1);
        table.put(1, 0);
        int t = 1;
//        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
//            int n = Integer.parseInt(br.readLine());
            int n = 1000000000;
//            System.out.println(findMinDays(n));
            System.out.println(findMinDays2(n));
            System.out.println();
        }
    }

    /**
     * f()
     * @param n
     * @return
     */

    public static int findMinDays(int n) {
        if (table.containsKey(n)) {
            return table.get(n);
        }

        if ((n & n - 1) == 0) {
            double val3 = Math.log10(n) / Math.log10(2);
            table.put(n, (int) val3);
            return table.get(n);
        }

        int val=Integer.MAX_VALUE;
        if (n % 2 == 0) {
            val = Math.min(findMinDays(n / 2), findMinDays(n - 1));
        } else {
            val = findMinDays(n - 1);
        }
        val = val+1;

        table.put(n, val);
        return table.get(n);
    }

    public static int findMinDays2(int n) {

        int[] table = new int[n+1];
        table[1]=0;
        table[2]=1;

        for(int i=3;i<=n;i++) {
            if(i%2==0) {
                table[i] = Math.min(table[i / 2], table[i - 1])+1;
            } else{
                table[i]= table[i-1]+1;
            }
        }
        return table[n];
    }
}
