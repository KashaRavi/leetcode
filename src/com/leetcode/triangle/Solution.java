package com.leetcode.triangle;

import java.util.*;

/**
 * Created by rkasha on 11/8/2014.
 */
public class Solution {
    public static void main(String[] args){
        Solution mySoln = new Solution();
        List<List<Integer>> input = Arrays.asList(Arrays.asList(-1),Arrays.asList(3,2),Arrays.asList(1,-2,-1));

        System.out.println(mySoln.minimumTotal(input));
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if(m == 0)
            return 0;

        int globalMin = Integer.MAX_VALUE;
        ArrayList<Integer> prevLevelList = new ArrayList<Integer>();
        prevLevelList.add(0);

        for(int i=0;i<m;i++){
            int n = triangle.get(i).size();
            ArrayList<Integer> currLevelList = new ArrayList<Integer>();
            for(int j=0;j<n;j++){

                if( (j < n - 1) && (j > 0)){
                    currLevelList.add(triangle.get(i).get(j) + prevLevelList.get(j - 1));
                    currLevelList.set(j,Math.min(currLevelList.get(j),triangle.get(i).get(j)+prevLevelList.get(j)));
                }
                else if(j == 0){
                    currLevelList.add(triangle.get(i).get(j)+prevLevelList.get(j));
                }
                else {
                    currLevelList.add(triangle.get(i).get(j)+prevLevelList.get(j-1));
                }

                if(i== m -1 )
                    globalMin = Math.min(globalMin,currLevelList.get(j));
            }
            //prevLevelList.clear();
            prevLevelList = currLevelList;

        }

        return globalMin;
    }
}
