package datastructures.Arrays;

/**
 * Created by rkasha on 3/22/19.
 */
public class FindFirstOccurrence {


    public static void main(String[] args) {
        //        int arr[] = { 2, 3, -1, -4 };
        //        int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        int arr[] ={1, 2, 3, 3, 1, 4};
        System.out.println(findFirstOccurrence(arr, 0, arr.length-1));
        ArrayUtils.printArray(arr);
    }

    public static int findFirstOccurrence(int[] arr, int start, int end){

        if(start<=end){
            int mid = (start+end)/2;
            if((mid-1<0 || arr[mid-1]!=mid-1) && arr[mid]==mid){
                return mid;
            }
            int i= findFirstOccurrence(arr, start, mid);
            if(i!=-1) return i;

            return findFirstOccurrence(arr, mid+1,end);
        }
        return -1;
    }
}
