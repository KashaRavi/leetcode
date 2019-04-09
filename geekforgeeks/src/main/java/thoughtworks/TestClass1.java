package thoughtworks;

import datastructures.Arrays.ArrayUtils;

/**
 * Created by rkasha on 4/6/19.
 */
public class TestClass1 {
    public static void main(String[] args) {
        int[] arr = { 1, 11, 7, 9, 10, 1, 2,3,3 };
        int[] q = { 1, 2, 3, 2, 5, 1, 5, 6, 1 };
        //        int[] arr = { 3, 7, 9, 10, 1, 2 };
//        int[] q = { 1, 1, 3, 2, 5, 1 };
        int[] ans = stairClimbingProblem(arr, q);
        ArrayUtils.printArray(ans);
    }

    /**
     * This can be better implemented with BST instead of linear search. But still it is timing out
     * in the challenge. The idea is to use online sorting algorithm. I don't know any such algorithms
     * for sorting an incoming stream of numbers. One such suggestion is to go with tree sort kind of
     * algorithm
     *
     * @param arr
     * @param q
     * @return
     */
    public static int[] stairClimbingProblem(int[] arr, int[] q) {

        int[] ans = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    int val = arr[i];
                    //right rotate by 1 elem.
                    int k = i;
                    while (k > j) {
                        arr[k] = arr[k - 1];
                        k--;
                    }
                    arr[j] = val;
                    break;
                }
            }
            ans[i] = arr[q[i] - 1];
        }
        ArrayUtils.printArray(arr);
        return ans;
    }
}
