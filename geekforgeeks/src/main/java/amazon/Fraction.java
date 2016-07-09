package amazon;

/**
 * Created by rkasha on 01-Jul-16.
 */

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Fraction {
    public static void main(String[] args) {
        //code
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int d = sc.nextInt();
            int base = n / d;
            int r = n % d;
            boolean hasDecimal = r != 0;
            n = r * 10;
            Map<Integer, Integer> decMap = new LinkedHashMap<Integer, Integer>();
            int repeatingKey = -1;
            while (r != 0) {
                if (!decMap.containsKey(n)) {
                    decMap.put(n, n / d);
                    r = n % d;
                    n = r * 10;

                } else {
                    repeatingKey = n;
                    break;
                }
            }
            System.out.print(base);
            StringBuffer sbf = new StringBuffer();
            if (hasDecimal) {
                sbf.append(".");

                for (Map.Entry<Integer, Integer> e : decMap.entrySet()) {
                    if (e.getKey() == repeatingKey) {
                        sbf.append("(");
                    }
                    sbf.append(e.getValue());
                }

                if (repeatingKey != -1) {
                    sbf.append(")");
                }

            }
            System.out.println(sbf.toString());

        }
    }
}