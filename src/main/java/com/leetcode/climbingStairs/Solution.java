package com.leetcode.climbingStairs;

/**
 * Created by rkasha on 11/2/2014.
 */
public class Solution {
    public int climbStairs(int n) {
        int numWaysToCurrentStep=1;
        int numWaysToPrevStep = 1;
        int numWaysToStepBeforePrevStep=1;

        for(int i=2;i<=n;i++){
            numWaysToCurrentStep = numWaysToPrevStep+numWaysToStepBeforePrevStep;
            numWaysToStepBeforePrevStep=numWaysToPrevStep;
            numWaysToPrevStep = numWaysToCurrentStep;
        }
        return numWaysToCurrentStep;

    }
}
