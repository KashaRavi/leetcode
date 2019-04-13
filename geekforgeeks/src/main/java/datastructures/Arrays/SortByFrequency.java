//https://www.geeksforgeeks.org/sort-elements-by-frequency/
package datastructures.Arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkasha on 4/13/19.
 */
public class SortByFrequency {
    public static void main(String[] args)
    {
        //        int arr[] = {1, 2, 3, 1, 3, 6, 6};
        //        int arr[] = {4,5,2,25};
        Integer arr[] = {2, 5, 2, 8, 5, 6, 8, 8};
//        Integer arr[] = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8};
        sortByFreq(arr);
        ArrayUtils.printArray(arr);
    }

    public static void sortByFreq(Integer[] arr){
        Map<Integer,Integer> freqMap = new HashMap<>();
        Map<Integer,Integer> indexMap = new HashMap<>();

        for(int i=0;i<arr.length;i++){
            freqMap.putIfAbsent(arr[i],0);
            indexMap.putIfAbsent(arr[i], i);
            freqMap.put(arr[i], freqMap.get(arr[i])+1);
        }

        Arrays.sort(arr, (a,b)-> {
            if(freqMap.get(a) == freqMap.get(b)){
                return indexMap.get(a)-indexMap.get(b);
            }
            return -(freqMap.get(a)-freqMap.get(b));
        });
    }
}
