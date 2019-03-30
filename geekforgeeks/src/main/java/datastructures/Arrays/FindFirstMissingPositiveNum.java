package datastructures.Arrays;

/**
 * Created by rkasha on 3/30/19.
 */
public class FindFirstMissingPositiveNum {

    public static void main(String[] args) {
        int arr[] = { 1, -2, -3, -1, -3, -6, -1, 2 };
//        int arr[] = { 1,2,4,3,6 };
//        int arr[] = { 1,2,4,3,5 };
        int arr_size = arr.length;
        System.out.println(findFirstPositiveNum(arr));
        ArrayUtils.printArray(arr);

    }

    public static int findFirstPositiveNum(int[] arr) {
        int n = arr.length;
        //step 1: segregate into non-positive and positive numbers
        int firstPositiveNumIndex = segregate(arr, n);

        //step 2: reset all negative numbers to zero
        for(int i=0;i<firstPositiveNumIndex;i++){
            arr[i]=0;
        }

        //step 3: use negation concept to record what numbers are available
        for (int j = firstPositiveNumIndex; j < n; j++) {
            int absoluteVal = Math.abs(arr[j]);
            if (absoluteVal <= n) {
                int ind = absoluteVal%n;
                if (ind == 0) {
                    arr[ind] = -absoluteVal;
                } else if (arr[ind] == 0) {
                    arr[ind] = -ind;
                } else if (arr[absoluteVal%n] > 0) {
                    arr[ind] = -arr[ind];
                }
            }
        }

        //return first index where array value is non-negative.
        for (int i = 1; i <= n; i++) {
            if (arr[i%n] >= 0) {
                return i;
            }
        }

        return n+1;
    }

    static int segregate(int arr[], int size) {
        int j = 0, i;
        for (i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
    }
}
