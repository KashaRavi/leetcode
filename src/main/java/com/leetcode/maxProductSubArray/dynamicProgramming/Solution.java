package com.leetcode.maxProductSubArray.dynamicProgramming;

/**
 * Created by rkasha on 10/28/2014.
 */

public class Solution {
    public static void main(String[] args){
        Solution mySoln = new Solution();
        int[] A = null;

        A = new int[]{-1,-1};
        System.out.println(mySoln.maxProduct(A));

        A = new int[]{-2,-3,-3,0,4};
        System.out.println(mySoln.maxProduct(A));

        A = new int[]{-2,0,-3,0,-3,0,-4,0,-1,0,-5,0,-5};
        System.out.println(mySoln.maxProduct(A));

        A = new int[]{0,33,-1,5,43,0,0,0};
        System.out.println(mySoln.maxProduct(A));
    }
    private int maxProduct(int[] A){
        int n=A.length;

        if(n > 0){
            int max_prod = A[0];
            int min_prod = A[0];
            int max_start_ind=0;
            int min_start_ind=0;
            int global_max = max_prod;
            int global_max_start_ind = 0;
            int global_max_end_ind=0;

            for(int i=1;i<n;i++){
                //update mac_product and it max_start_ind
                int temp_max_prod = max_prod;
                int temp_max_start_ind = max_start_ind;
                int temp = max_prod*A[i];
                if(temp > A[i]){
                    max_prod = temp;
                    max_start_ind = max_start_ind;
                } else {
                    max_prod = A[i];
                    max_start_ind = i;
                }
                temp = min_prod*A[i];
                if(temp > max_prod)
                {
                    max_prod = temp;
                    max_start_ind = min_start_ind;
                }

                //update_min_product and min_start_ind
                temp = min_prod*A[i];
                if(temp < A[i])
                {
                    min_prod = temp;
                    min_start_ind = min_start_ind;
                } else {
                    min_prod = A[i];
                    min_start_ind = i;
                }

                temp = temp_max_prod*A[i];
                if(temp < min_prod)
                {
                    min_prod = temp;
                    min_start_ind = temp_max_start_ind;
                }

                //update global max value and the corresponding indices
                if(global_max < max_prod){
                    global_max = max_prod;
                    global_max_start_ind = max_start_ind;
                    global_max_end_ind = i;
                }
            }
            System.out.println("Index[Start]:" + global_max_start_ind + " [End:] " + global_max_end_ind );
            return global_max;
        } else{
            System.out.println("Empty Array is inputted");
            return Integer.MIN_VALUE;
        }
    }
}
