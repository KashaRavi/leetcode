//https://www.geeksforgeeks.org/find-the-first-missing-number/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/17/19.
 */
public class MissingNumber {

    public static void main(String[] args) {
        //        int arr[] = { 1, 2, 3, 1, 3, 6, 6 };
//        int arr[] = { 0, 1, 2, 6, 9 };
//        int arr[] = { 4, 5, 10, 11 };
        int arr[] = { 0, 1, 2, 3 };

        System.out.println(findMissingNumber(arr, 0, arr.length - 1));
    }

    /**
     * This problem can be solved by modifying binary search. This is possible because of the given
     * constraints
     * 1.arr is sorted
     * 2.each element value is in between 0 and m-1
     * 3.m>n and n is size of arr.
     */

    /**
     * We have to find the minimum missing number.
     * Observation: If the array has no missing number then arr[i] will be equal to i.
     */
    public static int findMissingNumber(int[] arr, int start, int end) {

        if (start > end) {
            return end + 1;
        }
        //Within the given subarray the first non-matching element will be the required missing elem.
        if (arr[start] != start)
            return start;

        int mid = (start + end) / 2;

        if (arr[mid] == mid) {
            //This means all the elems from start till mid are at their right positions and there is no
            //missing number from start to mid.
            return findMissingNumber(arr, mid + 1, end);
        }
        return findMissingNumber(arr, start, mid);

    }
}

