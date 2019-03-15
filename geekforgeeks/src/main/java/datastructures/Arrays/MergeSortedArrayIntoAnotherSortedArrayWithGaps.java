//https://www.geeksforgeeks.org/merge-one-array-of-size-n-into-another-one-of-size-mn/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/14/19.
 */
public class MergeSortedArrayIntoAnotherSortedArrayWithGaps {
    public static void main(String[] args) {

        Integer arr1[] = { 1, null, 2, 4, null, null, 6, 6, null, 7, 7 };
        Integer arr2[] = { 3, 4, 4, 5 };
        mergeSortedArrays(arr1, arr2);
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }
    }

    public static void mergeSortedArrays(Integer arr1[], Integer arr2[]) {
        int m = arr1.length;
        int n = arr2.length;

        //move all the non-values to the left of the array 'arr1'.

        int i = 0;
        for(int j=0;j<m;j++) {
            if(arr1[j] != null){
                arr1[i]=arr1[j];
                i++;
            }
        }

        //There can be non-null values from i to m-1 becuase we just copied the values to the front without nullifying them.
        // And we dont have to worry about the actual value present because we will be anyhow overriding that value while
        //merging the arrays.


        //merge arr2 into arr1.
        //follow logic similar to merge sort
        //Note that mergeIndex never crosses pointer of first array
        int end1 = i - 1;
        int end2 = n - 1;
        int mergeIndex = m - 1;

        while (end1 >= 0 && end2 >= 0) {
            if (arr1[end1] >= arr2[end2]) {
                arr1[mergeIndex] = arr1[end1];
                end1--;
            } else {
                arr1[mergeIndex] = arr2[end2];
                end2--;
            }
            mergeIndex--;
        }

        while (end1 >= 0) {
            arr1[mergeIndex] = arr1[end1];
            end1--;
            mergeIndex--;
        }
        while (end2 >= 0) {
            arr2[mergeIndex] = arr2[end2];
            end2--;
            mergeIndex--;
        }
    }
}
