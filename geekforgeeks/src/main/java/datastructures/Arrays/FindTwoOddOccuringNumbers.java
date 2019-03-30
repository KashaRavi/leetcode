package datastructures.Arrays;

/**
 * Created by rkasha on 3/31/19.
 */
public class FindTwoOddOccuringNumbers {
    public static void main(String[] args) {
        //        int arr[] = { 2, 3, -1, -4 };
        //        int arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
//        int arr[] ={1, 2, 3, 3, 1, 4};
//        int arr[] ={12, 23, 34, 12, 12, 23, 12, 45};
//
//        int arr[] = {4, 4, 100, 5000, 4, 4, 4, 4, 100, 100};

        int arr[] = {10, 20};
//        ArrayUtils.printArray(arr);
        findOddOccuringNumbers(arr);
    }

    public static void findOddOccuringNumbers(int[] arr){
        //let z= x^y
        int z=0;
        int n = arr.length;
        for(int i=0;i<n;i++){
            z = z^arr[i];
        }

        //find the least significant bit that is set to 1. let it be mth bit
        int lsbNum = z^(z-1);

        //if mth bit is set in x^y that means either x has mth bit set or y but not both.
        //let us assume x has mth bit set;
        int x=0;
        for(int i=0;i<n;i++){
            if((lsbNum&arr[i]) !=0){
                x = x^arr[i];
            }
        }

        int y = x^z;
        System.out.println("x="+x+" y="+y);
    }
}
