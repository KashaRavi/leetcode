package com.leetcode.longestValidParenthesis;

import java.util.Stack;

/**
 * Created by rkasha on 11/7/2014.
 */
public class Solution {
    public int longestValidParentheses(String s) {
    int maxLen = 0;
        int lastUnMatchedClosingParenthesisIndex = -1;
        if(s.length() == 0 || s.length() == 1)
            return 0;

        Stack<Integer> stk = new Stack<Integer>();
        for(int currentIndex = 0;currentIndex< s.length();currentIndex++){
        if(s.charAt(currentIndex) == '(') {
            stk.push(currentIndex);
        } else {
            if(stk.isEmpty()) {
                lastUnMatchedClosingParenthesisIndex = currentIndex;
            } else {
                stk.pop();
                if(stk.isEmpty()){
                    maxLen = Math.max(maxLen,currentIndex-lastUnMatchedClosingParenthesisIndex);
                } else{
                    maxLen = Math.max(maxLen,currentIndex-stk.peek());
                }
            }
        }
        }
        return  maxLen;
    }
}
