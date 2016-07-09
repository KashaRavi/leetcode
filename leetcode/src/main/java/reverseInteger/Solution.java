package com.leetcode.reverseInteger;

/**
 * Created by rkasha on 10/25/2014.
 */
public class Solution {
    public static void main(String[] args) {
        Solution mySol = new Solution();
        int i=-312;
        System.out.println("Reverse of "+i+" is "+mySol.reverse(i));
    }

    public int reverse(int num) {
        int  sign = (num < 0)?-1:1;
        if(num < 0)
        {
            sign = -1;
            num = sign * num;
        } else {
            sign =1;
        }
        int rev_num = 0;
        while(num > 0)
        {
            rev_num = rev_num*10 + num%10;
            num = num/10;
        }
        return sign * rev_num;
    }
}
