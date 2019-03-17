//https://www.geeksforgeeks.org/check-if-array-elements-are-consecutive/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/17/19.
 */

//if all elements are consecutive then number of elements counted as max-min+1 will be equal to n.
//But that condition is not sufficient because there can be duplicates that cover for the missing numbers.
//So if max-min+1==n and if there are no duplicates then there is only one possibility that
// all the elements should be distinct and consecutive.

//Note: This method wont work if zero is present in the array. e.g: {0, 3, 0, 1} and {2, 2, 0, 3}.
// This is because -1*0 = 0.
//this also wont work for negative if there negative numbers in the original array.
//note: we are modifying the original array.
public class ConsecutiveIntegerArray {
    public static void main(String[] args) {
//        int arr[] = { 1, 2, 3, 1, 3, 6, 6 };
        int arr[] = {5, 4, 2, 3, 1, 6};

        // int arr[] = {2, 2, 0, 3};//does not work for this input.

        System.out.println(areAllConsecutive(arr));
    }

    public static boolean areAllConsecutive(int[] arr) {

        int n = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        if (max - min + 1 == n) {
            //use the concept of resuing the index values to record the information that an element
            // is already seen earlier.
            for (int i = 0; i < n; i++) {
                int index = Math.abs(arr[i]) - min;
                if(arr[index]<0){
                    //arr[i] is repeated in the array
                    return false;
                } else {
                    arr[index] = -arr[index];
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

