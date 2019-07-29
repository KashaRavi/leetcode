package datastructures.Arrays;
//https://www.geeksforgeeks.org/minimum-length-subarray-sum-greater-given-value/

/**
 * Created by rkasha on 7/29/19.
 */
public class minSubArray {
    public static void main(String[] args) {
        //        int arr[] = {1, 2, 3, 1, 3, 6, 6};
        //        int arr[] = {4,5,2,25};
//        int arr[] = { 1, 4, 45, 6, 0, 19 };
        //        printMinLengthSubArray(arr, 51);
//        int arr[] = {1, 10, 5, 2, 7};
//        printMinLengthSubArray(arr, 9);
//        int arr[] = {1, 11, 100, 1, 0, 200, 3, 2, 1, 250};
        //        printMinLengthSubArray(arr, 280);
        int arr[] = {1, 2, 4};
                printMinLengthSubArray(arr, 8);
    }

    public static void printMinLengthSubArray(int[] arr, int val) {
        int len = -1;
        int start = 0;
        int end = 0;
        int sum = 0;
        int globalStart = -1;
        int globalEnd = -1;

        while (end < arr.length) {
            if (start > end) {
                end = start;
            }
            sum += arr[end];
            while (sum > val) {
                if (len == -1 || end - start + 1 < len) {
                    globalStart = start;
                    globalEnd = end;
                    len = end - start + 1;
                }

                sum -= arr[start];
                start++;
            }
            end++;
        }

        System.out.println("start=" + globalStart + " and end=" + globalEnd + " len=" + len);

    }
}
