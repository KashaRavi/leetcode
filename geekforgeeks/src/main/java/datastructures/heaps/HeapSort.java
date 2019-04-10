package datastructures.heaps;

import datastructures.Arrays.ArrayUtils;

/**
 * Created by rkasha on 4/10/19.
 */
public class HeapSort {
    public static void main(String args[]) throws Exception {
        int arr[] = { 2, 3, 1, 4, 5, 6, 4, 7, 4, 8, 9 };
        int k = 5;
        heapSort(arr);
        ArrayUtils.printArray(arr);
    }

    public static void heapSort(int[] arr) {
        MinHeap h = new MinHeap(arr);
        h.buildHeap();

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = h.extractMin();
            arr[i] = val;
        }
    }
}
