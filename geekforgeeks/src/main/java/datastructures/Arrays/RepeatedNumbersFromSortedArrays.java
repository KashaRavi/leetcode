package datastructures.Arrays;

import java.util.ArrayList;

public class RepeatedNumbersFromSortedArrays {

    public static void main(String[] arr) {
        int[] nums1 = { 1, 2, 3, 3, 4 };
        int[] nums2 = { 2, 4, 4, 5 };
        //        int[] nums1 = {1, 2, 3, 5, 6, 7};
        //        int[] nums2 = {3, 6, 7, 8, 20};
        ArrayUtils.printArray(findDuplicates(nums1, nums2));
    }

    public static ArrayList<Integer> findDuplicates(int[] nums1, int[] nums2) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        while ((i < nums1.length) && (j < nums2.length)) {
            if (nums2[j] == nums1[i]) {
                result.add(nums1[i]);
                while ((i < nums1.length) && (nums1[i] == nums2[j])) {
                    i++;
                }
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
