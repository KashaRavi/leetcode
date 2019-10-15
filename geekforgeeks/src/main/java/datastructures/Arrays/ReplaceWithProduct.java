package datastructures.Arrays;

public class ReplaceWithProduct {

    public static void main(String[] args) {

        int arr[] = {2, 3, 4, 5, 6};

        replace(arr);
        ArrayUtils.printArray(arr);
    }

    public static void replace(int[] arr) {
        int n= arr.length;
        int prev=arr[0];
       for(int i=0;i<n-1;i++){
           int nextPrev=arr[i];
           arr[i]=prev*arr[i+1];
           prev=nextPrev;
       }
       arr[n-1]=prev*arr[n-1];
    }
}
