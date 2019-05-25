//https://www.geeksforgeeks.org/nearly-sorted-algorithm/
package datastructures.Arrays;

import datastructures.heaps.MinHeap;

/**
 * Created by rkasha on 5/25/19.
 */
public class SortNearlySortedArray {
    public static void main(String[] args) throws Exception {
        //        int arr[] = {6, 5, 3, 2, 8, 10, 9 };int k=3;
        int arr[] = { 10, 9, 8, 7, 4, 70, 60, 50 };
        int k = 4;
        ArrayUtils.printArray(sortNearlySortedArray(arr, k));
    }

    public static int[] sortNearlySortedArray(int[] arr, int k) throws Exception {
        MinHeap heap = new MinHeap(arr, 0, k);

        int j = 0;
        for (int i = k + 1; i < arr.length; i++, j++) {
            arr[j] = heap.extractMin();
            ;
            heap.insertKey(arr[i]);
        }

        while (heap.getHeapSize() > 0) {
            arr[j] = heap.extractMin();
            j++;
        }

        return arr;

    }

}
