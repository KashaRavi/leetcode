//https://www.geeksforgeeks.org/find-fixed-point-value-equal-index-given-array-duplicates-allowed/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/26/19.
 */
public class FixedPointWithDuplicates {
    public static void main(String[] args) {
        int arr[] = { -10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13 };
        int res = findAnyFixedPointWithoutSkipping(arr, 0, arr.length - 1);
        System.out.println(res);
    }

    public static int findAnyFixedPointWithoutSkipping(int[] arr, int start, int end) {

        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return mid;
        }

        int fixedPointInLeftSubarray = findAnyFixedPointWithoutSkipping(arr, start, mid - 1);

        //since duplicates are allowed, the required element can be found in either left or right subarray.
        //so we cannot take a chance and we have to search in both the subarrays.
        //Though it looks like binary search in the worst case it is a O(n) solution.
        if (fixedPointInLeftSubarray >= 0) {
            return fixedPointInLeftSubarray;
        }

        return findAnyFixedPointWithoutSkipping(arr, mid + 1, end);

    }

    /**
     *
     * When searching for A[i]=i, there is an opprtunity to skip some elements either in the left
     * subarray or in the right subarray depending on the actual value of arr[mid].
     *
     * if arr[mid]<mid, then in the left subarray search, we can skip all the indices that are strictly greater than arr[mid].
     * But we still have to search right subarray if nothing is found in the left subarray.
     *
     * if arr[mid]>mid, then in the right subarray search, we can skip all the indices that are strictly lesser than arr[mid].
     * These two conditions can be simply combined as shown in the code. We are searching in the right subarray because we did not
     * find fixed point in the left subarray.
     */
    public static int findAnyFixedPointWithSkipping(int[] arr, int start, int end) {

        if (start > end) {
            return -1;
        }

        int mid = (start + end) / 2;
        if (arr[mid] == mid) {
            return mid;
        }

        int fixedPointInLeftSubarray = findAnyFixedPointWithSkipping(arr, start,
                Math.min(arr[mid], mid - 1));

        //since duplicates are allowed, the required element can be found in either left or right subarray.
        //so we cannot take a chance and we have to search in both the subarrays.
        //Though it looks like binary search in the worst case it is a O(n) solution.
        if (fixedPointInLeftSubarray >= 0) {
            return fixedPointInLeftSubarray;
        }

        return findAnyFixedPointWithSkipping(arr, Math.max(arr[mid], mid + 1), end);

    }
}
