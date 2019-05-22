//https://www.geeksforgeeks.org/sliding-window-maximum-maximum-of-all-subarrays-of-size-k/

package datastructures.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by rkasha on 5/21/19.
 */
public class MaxOfConsecutiveSubarrays {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6};
//        int arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
        int k=3;
        ArrayUtils.printArray(findMaxOfConsecutiveSubarrays(arr, k));
    }

    public static int[] findMaxOfConsecutiveSubarrays(int[] arr, int k) {

        int numWindows = arr.length - k + 1;
        int[] result = new int[numWindows];
        int currWindow = 0;

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()]) {
                queue.removeLast();
            }

            queue.add(i);
        }

        for (int i = k; i < arr.length; i++) {
            result[currWindow++] = arr[queue.peekFirst()];

            if (i - queue.peekFirst() >= k) {
                queue.removeFirst();
            }

            while (!queue.isEmpty() && arr[i] >= arr[queue.peekLast()] ) {
                queue.removeLast();
            }

            queue.add(i);
        }

        result[currWindow] = arr[queue.peekFirst()];

        return result;

    }
}
