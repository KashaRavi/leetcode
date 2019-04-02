package datastructures.heaps;

import datastructures.Arrays.ArrayUtils;

/**
 * Created by rkasha on 4/2/19.
 */
public class MinHeap {

    int heapSize;
    int capacity;
    int arr[];

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return left(i) + 1;
    }

    public void insertKey(int val) throws Exception {
        if (heapSize <= capacity) {
            int i = heapSize;
            heapSize++;
            arr[i] = val;

            int parent = parent(i);
            while (i > 0 && arr[i] < arr[parent]) {
                int tmp = arr[i];
                arr[i] = arr[parent];
                arr[parent] = tmp;
                i = parent;
                parent = parent(i);
            }
        } else {
            throw new IllegalAccessException();
        }
    }

    public void buildHeap() {
        for (int i = parent(this.arr.length - 1); i >= 0; i--) {
            minHeapify(i);
        }
    }

    public int getMin() throws IllegalAccessException {
        if (heapSize > 0) {
            return arr[0];
        }

        throw new IllegalAccessException();
    }

    public int extractMin() {
        int val = arr[0];
        arr[0] = arr[heapSize - 1];
        heapSize--;
        minHeapify(0);
        return val;
    }

    public void decreasekey(int i, int new_val) {
        arr[i] = new_val;
        //percolate up
        int parent = parent(i);
        while (i > 0 && arr[i] < arr[parent]) {
            int tmp = arr[i];
            arr[i] = arr[parent];
            arr[parent] = tmp;
            i = parent;
            parent = parent(i);
        }
    }

    public void deleteKey(int i) {
        decreasekey(i, Integer.MIN_VALUE);
        extractMin();
    }

    public void minHeapify(int i) {

        while (true) {
            int l = left(i);
            int r = right(i);

            int smallest = i;
            if (l < heapSize && arr[l] < arr[i]) {
                smallest = l;
            }
            if (r < heapSize && arr[r] < arr[smallest]) {
                smallest = r;
            }

            if (smallest != i) {
                int tmp = arr[i];
                arr[i] = arr[smallest];
                arr[smallest] = tmp;
                i = smallest;
            } else {
                break;
            }
        }
    }

    public static void main(String args[]) {
        testHeapOps();
        testBuildHeap();
    }

    public static void testHeapOps() {
        MinHeap h = new MinHeap(5);
        try {

            h.insertKey(3);
            h.insertKey(2);
            h.deleteKey(1);
            h.insertKey(15);
            h.insertKey(5);
            h.insertKey(4);
            h.insertKey(45);
            h.insertKey(1);
            System.out.println(h.getMin());
            h.printHeap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testBuildHeap() {
        int[] values = { 3, 4, 5, 2, 6, 8, 1 };
        ArrayUtils.printArray(values);
        MinHeap h = new MinHeap(values);
        h.buildHeap();
        h.printHeap();
    }

    public MinHeap(int capacity) {
        heapSize = 0;
        this.capacity = capacity;
        arr = new int[this.capacity];
    }

    public MinHeap(int[] values) {
        heapSize = values.length;
        this.capacity = values.length;
        arr = values;
    }

    public void printHeap() {
        ArrayUtils.printArray(this.arr);
    }
}
