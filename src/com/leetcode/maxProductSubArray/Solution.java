package com.leetcode.maxProductSubArray;

/**
 * Created by rkasha on 10/27/2014.
 */
public class Solution {
    int maxProduct(int A[]) {
        int n = A.length;
        if (n==0) return 0;

        int maxi = 1, mini = 1;
        int out = Integer.MIN_VALUE;

        for (int i=0;i<n;i++) {

            if (A[i] > 0) {
                maxi *= A[i];
                mini *= A[i];
                out = Math.max(out,maxi);
            } else if (A[i] == 0) {
                out = Math.max(out,0);
                maxi = 1;
                mini = 1;
            } else {
                out = Math.max(out,mini*A[i]);
                int oldmaxi = maxi;
                maxi = Math.max(1,mini*A[i]);
                mini = oldmaxi*A[i];
            }

        }

        return out;
    }
}
