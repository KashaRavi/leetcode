package datastructures.Arrays;

/**
 * Created by rkasha on 3/14/19.
 */
public class MaxSumSubArray {

    public static void main(String[] args) {

        //        int arr[] = {1, 2, 1, 3, 4, 2, 3};
        //                int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};
        //        int arr[] = {8,10,2};
        //        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4, 4};
//        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
//        int arr[] = {-2, -3, -4, -1, -2, -1, -5, -3};
        int arr[] = {4, -1, 5};

        int[] res= computeMaxSubArraySum(arr);
        System.out.printf("Largest contiguous startIndex=%d, endIndex=%d, sum=%d%n",
                res[0], res[1], res[2]);
        System.out.println(res[2]);
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

    public static int[] computeMaxSubArraySum(int[] arr) {
        int startIndex = 0;
        int endIndex = 0;
        int sum = arr[0];
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum >= maxSum) {
                maxSum = sum;
                endIndex = i;
            }
            if (arr[i] > maxSum) {
                startIndex = i;
                maxSum = sum = arr[i];
            }
            if (endIndex < startIndex)
                endIndex = startIndex;
        }
        int[] res = new int[3];
        res[0]=startIndex;
        res[1]=endIndex;
        res[2]=maxSum;
        return res;
    }

    public static int getMaxSubArraySum(int[] arr) {
        return computeMaxSubArraySum(arr)[2];
    }
}
