package com.leetcode.reverseWordsOfString;

/**
 * Created by rkasha on 10/28/2014.
 */
public class Solution {
    public static void main(String[] args){
        Solution mySoln = new Solution();
        String input = "the sky is blue";
        input = " 1";
        System.out.println(mySoln.reverseWords(input));
    }

    String reverseWords(String s){
        String[] words= s.split("\\s+");
        int n = words.length;
        String out = "";
        if(n > 0)
        {
            out = words[n-1];
            for(int i = n-2;i >=0;i--)
            {
                if(!words[i].equals(""))
                out = out + " "+words[i];
            }
        }
        return out;
    }

}
