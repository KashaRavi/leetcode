package datastructures.Arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by rkasha on 3/25/19.
 */
public class LargestConcatenatedNumber {
    public static void main(String[] args) {
                Integer arr[] = { 1, 2, 3, 1, 3, 6, 6 };
//        Integer arr[] = {5, 4, 2, 3, 1, 6};
//        Integer arr[] = {80, 801};
//        Integer arr[] = {7,776,7,7};
//        Integer arr[] = {54,546,548, 60};

        concatenateArrayToLargestNumber(arr);
    }

    public static void concatenateArrayToLargestNumber(Integer[] arr) {
        Arrays.sort(arr, (n1, n2)-> {
            String x= String.valueOf(n1);
            String y= String.valueOf(n2);
            return -(x+y).compareTo(y+x);
        });
        for(int i:arr){
            System.out.print(i);
        }
    }
}
