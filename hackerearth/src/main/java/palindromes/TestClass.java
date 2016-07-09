package palindromes;

/**
 * @author rkasha
 */

/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/

import java.util.*;

class TestClass {
    public static void main(String args[] ) throws Exception {
        System.out.println(Integer.MAX_VALUE-1000000007);
        /*
         * Read input from stdin and provide input before running

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            System.out.println("hello world");
        }
        */
        char[] chars = new char[6];
        chars[1] ='a';
        chars[2] ='b';
        chars[3] ='c';
        chars[4] ='d';
        chars[5] ='e';
        Random random = new Random();
        int count=0;
        List<String> pals = new ArrayList<String>();

        for(int i=1;i<100;i+=2) {
            char[] palArray = new char[i];
            int primeVal = 0;
            int k = i - 1;
            for (int j = 0; j <= k; ) {
                int rand = random.nextInt(5 - 1) + 1;
                char curr = chars[rand];
                if (k == j - 1) {

                    palArray[j] = chars[1];
                    primeVal++;
                    if (isPrime(primeVal)) {
                        pals.add(new String(palArray));}
                    
                        palArray[j] = chars[3];
                        primeVal += 2;
                        if (isPrime(primeVal)) {
                            pals.add(new String(palArray));
                        }
                    
                            palArray[j] = chars[5];
                            primeVal += 2;
                            if (isPrime(primeVal)) {
                                pals.add(new String(palArray));
                            }
                    
                        } else {
                            palArray[j] = curr;
                            primeVal += rand;
                        }
                        j++;
                        if (k > j) {
                            palArray[k] = curr;
                            primeVal += rand;
                            k--;

                        }

                    }
                    if (isPrime(primeVal)) {
                        pals.add(new String(palArray));

                    }

                }

                System.out.println(pals.size());
                for (int p = 0; p < pals.size(); p++)
                    
                    System.out.println(pals.get(p));

                //        System.out.println("Hello World!");
            }
        

        private static boolean isPrime(int num) {
            if (num == 2 ) return true;
            if (num % 2 == 0) return false;
            for (int i = 3; i * i <= num; i += 2)
                if (num % i == 0) return false;
            return true;
        }
    }


