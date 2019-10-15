//https://www.geeksforgeeks.org/rearrange-array-arrj-becomes-arri-j/
package datastructures.Arrays;

/**
 * solution implemented here is not complete and needs more cases to be handled like cycles in the array
 */
public class ValueIndexSwapper {
    public static void main(String[] args) {
        //        int arr[] = {1, 2, 3, 1, 3, 6, 6};
        //        int arr[] = {4,5,2,25};
        int arr[] = {1, 3, 0, 2};
        //        Integer arr[] = {2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8};
        swapValueWithIndex(arr);
        ArrayUtils.printArray(arr);
    }

    public static void swapValueWithIndex(int[] arr) {
        int src=0;
        int dest=arr[src];
        int temp=arr[dest];
        arr[dest]=src;
        src=dest;
        dest=temp;
        while(src !=0) {
            temp = arr[dest];
            arr[dest]=src;
            src=dest;
            dest=temp;
        }
    }
}
