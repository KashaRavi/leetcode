package datastructures.Arrays.maxsumsubarray;

/**
 * Created by rkasha on 3/14/19.
 */
public class TestClass {

    public static void main(String[] args) {

        //        int arr[] = {1, 2, 1, 3, 4, 2, 3};
        //                int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};
        //        int arr[] = {8,10,2};
        //        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4, 4};
//        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        int arr[] = {-2, -3, -4, -1, -2, -1, -5, -3};

        int k = 4;
        int output= findMaxSumSubArray(arr);
        System.out.println(output);
    }

    public static int findMaxSumSubArray(int[] arr) {
        int max = arr[0];
        int currSum = arr[0];
        int start = 0;
        int end = 0;
        int tempSubArrayStart=0;

        for(int i=1;i<arr.length;i++) {

            currSum += arr[i];

            if(currSum < arr[i]){
                currSum = arr[i];
                tempSubArrayStart=i;
            }

            if(currSum > max) {
                max = currSum;
                start = tempSubArrayStart;
                end = i;
            }
        }

        for(int i=start;i<=end;i++) {
            System.out.print(arr[i]+" ");
        }

        System.out.println();
        return max;
    }
}
