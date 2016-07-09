package com.leetcode.minimumElement;

/**
 * Created by rkasha on 10/25/2014.
 */
public class Solution {

    public static void main(String[] args){
        Solution mySolution = new Solution();
        int num1[] =  {5, 6, 1, 2, 3, 4};
        System.out.println("Min Element is:"+ mySolution.findMin(num1) );

        int num2[] =  {1, 2, 3, 4};
        System.out.println("Min Element is:"+ mySolution.findMin(num2) );

        int num3[] =  {1};
        System.out.println("Min Element is:"+ mySolution.findMin(num3) );

        int num4[] =  {1, 2};
        System.out.println("Min Element is:"+ mySolution.findMin(num4) );

        int num5[] =  {2, 1};
        System.out.println("Min Element is:"+ mySolution.findMin(num5) );

        int num6[] =  {5, 6, 7, 1, 2, 3, 4};
        System.out.println("Min Element is:"+ mySolution.findMin(num6) );

        int num7[] =  {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Min Element is:"+ mySolution.findMin(num7) );

        int num8[] =  {2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println("Min Element is:"+ mySolution.findMin(num8) );

        int num9[] =  {3, 4, 5, 1, 2};
        System.out.println("Min Element is:"+ mySolution.findMin(num9) );

        return;
    }


     public int findMin(int num[])
    {
        int low=0, high = num.length-1;
        while(true) {
            if (high < low) {
                return num[0];
            }

            if (high == low) return num[low];

            int mid = low + (high - low) / 2; /*(low + high)/2;*/

            if (mid < high && num[mid + 1] < num[mid])
                return num[mid + 1];

            if (mid > low && num[mid] < num[mid - 1])
                return num[mid];

            if (num[high] > num[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
}