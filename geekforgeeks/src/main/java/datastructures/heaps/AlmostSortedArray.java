//https://www.geeksforgeeks.org/nearly-sorted-algorithm/
package datastructures.heaps;

import datastructures.Arrays.ArrayUtils;

import java.util.PriorityQueue;

/**
 * Created by rkasha on 4/10/19.
 */
public class AlmostSortedArray {
    public static void main(String args[]) throws Exception {
//        int arr[] = {6, 5, 3, 2, 8, 10, 9};
        int arr[] = {10, 9, 8, 7, 4, 70, 60, 50};
        int k=4;
        ArrayUtils.printArray(arr);
        sort(arr,k);
        ArrayUtils.printArray(arr);
    }

    /**
     * I could have custom implementation of heap but to learn the usage of javas priority queue,
     * I'll be using it here.
     */
    public static void sort(int[] arr, int k){
        PriorityQueue<Integer> pq  = new PriorityQueue<>(k+1);
        int start =0; int end=0;
        for(end=0;end<=k;end++){
            pq.add(arr[end]);
        }

        while (!pq.isEmpty()) {
            int min = pq.remove();
            arr[start]=min;
            if(end < arr.length) {
                pq.add(arr[end]);
                end++;
            }
            start++;
        }
    }

}


