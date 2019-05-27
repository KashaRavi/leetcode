//https://www.geeksforgeeks.org/maximum-contiguous-circular-sum/
package datastructures.Arrays;

import static datastructures.Arrays.MaxSumSubArray.getMaxSubArraySum;

/**
 * Created by rkasha on 5/27/19.
 */
public class MaxSumCircularSubArray {
    public static void main(String[] args) {

        //        int arr[] = {8, -8, 9, -9, 10, -11, 12};
        //        int arr[] = {10, -3, -4, 7, 6, 5, -4, -1};
        int arr[] = { -1, 40, -14, 7, 6, 5, -4, -1 };

        int output = maxCircularSubArray(arr);
        System.out.println(output);
    }

    public static int maxCircularSubArray(int[] arr) {
        int maxSubArraySum = getMaxSubArraySum(arr);

        int totalSum = 0;
        for (int i = 0; i < arr.length; i++) {
            totalSum += arr[i];
        }

        //invert the array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = -arr[i];
        }

        int maxInvertedSubArraySum = getMaxSubArraySum(arr);

        int nonContributingSubArraySum = -maxInvertedSubArraySum;

        int maxCircularSubArraySum = totalSum - nonContributingSubArraySum;

        return Math.max(maxSubArraySum, maxCircularSubArraySum);
    }
}
