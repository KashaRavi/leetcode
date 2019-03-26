//https://www.geeksforgeeks.org/find-a-fixed-point-in-a-given-array/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/26/19.
 */
public class FixedPointWithDistinctElements {
    public static void main(String[] args)
    {
        int arr[] = {-10, -1, 0, 4, 10, 11, 30, 50, 100};
        int res = findAnyFixedPoint(arr, 0, arr.length-1);
        System.out.println(res);
    }

    public static int findAnyFixedPoint(int[] arr, int start, int end) {

        if(start > end){
            return -1;
        }

        int mid = (start+end)/2;
        if(arr[mid]==mid) {
            return mid;
        }
        //since the elements are sorted and distinct, the minimum diff between two elements when
        // you scan rom right to left i.e., from mid to start -1
        if(arr[mid] < mid){
            // The rate at which the values decreases is greater than or equal to the rate at which
            //index value decreases when when we scan from mid to start. Since already arr[mid] < mid,
            // it is guaranteed that there is no i(start<i<mid) to the left of mid where arr[i] can
            // be equal to i. But there is a chance that fixed point can be found in the right subarray.
            return findAnyFixedPoint(arr, mid+1, end);
        } else {
            //similar logic applies to the right branch.
            return findAnyFixedPoint(arr, start,mid-1);
        }
    }

}
