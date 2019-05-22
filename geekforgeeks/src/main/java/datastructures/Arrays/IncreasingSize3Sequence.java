//https://www.geeksforgeeks.org/find-a-sorted-subsequence-of-size-3-in-linear-time/
package datastructures.Arrays;

/**
 * Created by rkasha on 5/22/19.
 */
public class IncreasingSize3Sequence {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 1, 4, 5, 2, 3, 6 };
        ArrayUtils.printArray(increasingsequenceOfSize3(arr));
    }

    public static int[] increasingsequenceOfSize3(int[] arr) {
        int[] res = new int[3];
        int n = arr.length;

        int[] smaller = new int[n];

        smaller[0] = -1;
        int minIndex = 0;

        //construct an array that contains index of the min element to the left of current element
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[minIndex]) {
                smaller[i] = minIndex;
            } else {
                smaller[i] = -1;
                minIndex = i;
            }
        }

        //construct an array that contains index of the max element to the right of current element
        int[] larger = new int[n];
        larger[n - 1] = -1;
        int maxIndx = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[maxIndx]) {
                larger[i] = maxIndx;
            } else {
                larger[i] = -1;
                maxIndx = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (smaller[i] != -1 && larger[i] != -1) {
                res[0] = arr[smaller[i]];
                res[1] = arr[i];
                res[2] = arr[larger[i]];
                return res;
            }
        }

        return res;

    }
}
