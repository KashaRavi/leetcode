package com.leetcode.editDistance;

/**
 * Created by rkasha on 11/2/2014.
 */
public class Solution {

    public static void main(String[] args)
    {
        Solution mySoln = new Solution();
     System.out.println("kasha -> leetcode:"+mySoln.minDistance("kasha","leetcode"));

    }

    public int minDistance(String word1, String word2) {
        int[][] distArray = new int[word1.length()+1][word2.length()+1];
        distArray[0][0] = 0;

        for(int j =1 ;j< distArray[0].length;j++)
        {
            distArray[0][j] = j;
        }

        for(int i=1;i<distArray.length;i++)
        {
            distArray[i][0] = i;
        }

        int i,j;
        for(i=1;i < distArray.length;i++ )
        {
            for(j = 1; j< distArray[0].length;j++)
            {
                int replaceDist = distArray[i-1][j-1]+((word1.charAt(i-1) == word2.charAt(j-1))?0:1);
                int deleteDist = distArray[i-1][j]+1;
                int insertDist = distArray[i][j-1] +1;
                distArray[i][j] = Math.min(Math.min(replaceDist,deleteDist),insertDist);
            }
        }

        return distArray[word1.length()][word2.length()];

    }
}
