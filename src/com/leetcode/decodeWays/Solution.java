package com.leetcode.decodeWays;

/**
 * Created by rkasha on 11/2/2014.
 */
public class Solution {

    public static void main(String[] args){
        Solution mySoln = new Solution();
        String input = "";
        input = "10";
        System.out.println(mySoln.numDecodings(input));
    }

    public int numDecodings(String s) {
        if(s.length() == 0 || s.charAt(0) == '0')
            return 0;

        String newStr = "0"+s;
        int numPrevPrefDec =0;
        int numPrefDec = 1;
        int numCurrStrDecodings=0;
        int i=0;
        int j=1;

        for(;j<newStr.length();j++,i++) {
            char prevDigit = newStr.charAt(i);
            char currDigit = newStr.charAt(j);
            int revmappedDoubleDigitIntegerkey = Integer.parseInt(""+prevDigit+currDigit);

            numCurrStrDecodings = ((currDigit > '0')?1:0)*numPrefDec + ( (revmappedDoubleDigitIntegerkey > 9 && revmappedDoubleDigitIntegerkey < 27) ? 1 : 0) * numPrevPrefDec;
            numPrevPrefDec = numPrefDec;
            numPrefDec = numCurrStrDecodings;
        }

        return numCurrStrDecodings;
    }
}
