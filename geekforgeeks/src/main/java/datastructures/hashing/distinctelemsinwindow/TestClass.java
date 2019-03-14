package datastructures.hashing.distinctelemsinwindow;

import datastructures.Arrays.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkasha on 3/13/19.
 */
public class TestClass {

    public static void main(String[] args) {

//        int arr[] = {1, 2, 1, 3, 4, 2, 3};
        int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};

        int k = 4;
        int[] output=countDistinctElemsInAllWindows(arr,k);
        ArrayUtils.printArray(output);
    }

    public static int[] countDistinctElemsInAllWindows(int[] arr, int k) {
        int[] countArr = new int[arr.length-k+1];
        Map<Integer, Integer> map = new HashMap<>();

        int windowBegin = 0;
        int currIndex = windowBegin;
        for(currIndex=0;currIndex<k;currIndex++) {
            map.merge(arr[currIndex], 1, (a, b) -> a + b);
        }
        countArr[windowBegin] = map.size();

        while(currIndex < arr.length) {
            // remove the value at the beginning of the window.
            int startElem = arr[windowBegin];
            int v = map.get(startElem) - 1;

            if (v == 0) {
                map.remove(startElem);
            } else {
                map.put(startElem, v);
            }

            // insert the elem at currIndex
            int newElem = arr[currIndex];
            map.merge(newElem, 1, (a, b) -> a + b);
            windowBegin++;
            currIndex++;
            countArr[windowBegin] = map.size();
        }
        return countArr;
    }
}
