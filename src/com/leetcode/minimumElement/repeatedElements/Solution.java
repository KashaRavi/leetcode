package com.leetcode.minimumElement.repeatedElements;

/**
 * Created by rkasha on 10/25/2014.
 */
public class Solution {

    public static void main(String[] args){
        Solution mySolution = new Solution();
        int num1[] =  {5, 6, 1, 2, 3, 4};
        System.out.println("Min Element is:"+ mySolution.findMin(num1) );

        int num2[] =  {1, 1, 0, 1};
        System.out.println("Min Element is:"+ mySolution.findMin(num2) );

        int num3[] =  {1, 1, 2, 2, 3};
        System.out.println("Min Element is:"+ mySolution.findMin(num3) );

        int num4[] =  {3, 3, 3, 4, 4, 4, 4, 5, 3, 3};
        System.out.println("Min Element is:"+ mySolution.findMin(num4) );

        int num5[] =  {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2};
        System.out.println("Min Element is:"+ mySolution.findMin(num5) );

        int num6[] =  {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1};
        System.out.println("Min Element is:"+ mySolution.findMin(num6) );

        int num7[] =  {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2};
        System.out.println("Min Element is:"+ mySolution.findMin(num7) );

        int num8[] =  {3,3,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        System.out.println("Min Element is:"+ mySolution.findMin(num8) );

        int num9[] =  {1,3,3};
        System.out.println("Min Element is:"+ mySolution.findMin(num9) );

        int num10[] =  {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0};
        System.out.println("Min Element is:"+ mySolution.findMin(num10) );

        int num11[] =   {2,2,2,2,2,2,2,2};
        System.out.println("Min Element is:"+ mySolution.findMin(num11) );

        return;
    }


    public int findMin(int[] num) {
        return findMin(num,0,num.length-1);
    }

    int findMin(int arr[], int low, int high)
    {
        if (high < low)  return arr[0];

        if (high == low) return arr[low];

        int mid = low + (high - low)/2;

        if (mid < high && arr[mid+1] < arr[mid])
            return arr[mid+1];

        if (arr[low] == arr[mid] && arr[high] == arr[mid])
            return Math.min(findMin(arr, low, mid-1), findMin(arr, mid+1, high));

        if (mid > low && arr[mid] < arr[mid - 1])
            return arr[mid];

        if (arr[high] >= arr[mid])
            return findMin(arr, low, mid-1);
        return findMin(arr, mid+1, high);
    }
}
