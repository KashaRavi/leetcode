package com.leetcode.maxSumSubArray;

/**
 * Created by rkasha on 10/29/2014.
 */
public class Solution {
    public int maxSubArray(int[] A) {
        int n = A.length;

        if(n>0) {
            int max_sum = A[0];
            int global_max = max_sum;
            for(int i=1;i<n;i++){
                max_sum = Math.max(max_sum+A[i],A[i]);
                if(max_sum > global_max)
                    global_max = max_sum;
            }
            return global_max;
        }
        return Integer.MIN_VALUE;
    }
}
