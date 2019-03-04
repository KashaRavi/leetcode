package hitachi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkasha on 3/2/19.
 */
public class TestClass {

    /**
     * 1
     * 7 3
     * 1 2 3 4 5 6 7
     *
     *
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int t = Integer.parseInt(br.readLine());
        int t = 1;
        for (int i = 0; i < t; i++) {
//            String[] s = br.readLine().split("\\s+");
//            int n = Integer.parseInt(s[0]);
//            int k = Integer.parseInt(s[1]);
//            int[] arr = new int[n];
//            String[] numStrs = br.readLine().split("\\s+");
//            for(int j=0;j< n;j++) {
//                arr[j]= Integer.parseInt(numStrs[j]);
//            }

            int n= 8;
            int k=4;
            int[] arr = {1,2 ,3, 4 ,5 ,6, 7, 8};

            System.out.println(getMaxPrimeFrequency(arr, k));
        }
    }


    public static Map<Integer, Integer> getFreqMap(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for(int i=0;i<arr.length;i++) {
            if(!freqMap.containsKey(arr[i])) {
                freqMap.put(arr[i],0);
            }
            int f = freqMap.get(arr[i]);
            freqMap.put(arr[i], f+1);
        }
        return freqMap;
    }

    public static int getMaxPrimeFrequency(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> fMap = getFreqMap(arr);
        for(int f = n;f>=2;f--) {
            if(isPrime(f)) {
                if(n%f ==0) {
                    int d = n/f;
//                    d*f=n
                    //check if every frequency can be converted to d with at most k operations
                    if(isValid(fMap, f, k)) {
                        return f;
                    }
                }
            }
        }
        return -1;
    }


    public static boolean isValid(Map<Integer, Integer> fMap, int targetFreq, int k) {
        for(int val:fMap.keySet() ) {
            int numFreq = fMap.get(val);
            if(numFreq > targetFreq) {
                k = k-(numFreq-targetFreq);
            } else {
                k = k-(targetFreq-numFreq);
            }
            if(k<0)
                return false;
        }
        return true;
    }

    static boolean isPrime(int n) {
        for(int i=2;i<n;i++) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
