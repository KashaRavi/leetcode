package datastructures.heaps;

/**
 * Created by rkasha on 4/3/19.
 */
public class KthLargestElement {

    public static void main(String args[]) throws Exception {
        int arr[] = { 2, 3, 1, 4, 5, 6, 4, 7, 4, 8, 9 };
        int k = 5;
        getKLargestElements(arr, k);
    }

    public static void getKLargestElements(int[] arr, int k) throws Exception {
        MinHeap minHeap = new MinHeap(k);

        for (int val : arr) {

            if (minHeap.getHeapSize() >= k) {
                minHeap.extractMin();
            }
            minHeap.insertKey(val);

            minHeap.insertKey(val);
        }

        while (minHeap.getHeapSize() > 0) {
            System.out.println(minHeap.extractMin());
        }
    }
}
