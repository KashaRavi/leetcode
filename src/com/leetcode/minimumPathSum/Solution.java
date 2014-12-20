package com.leetcode.minimumPathSum;

/**
 * Created by rkasha on 10/29/2014.
 */
public class Solution {


    public static void main(String[] args)
    {
        Solution mySoln = new Solution();
        int [][] grid = {{1,2},{1,1}};
        System.out.println("kasha -> leetcode:"+mySoln.minPathSum(grid));

    }

    public int minPathSum(int[][] grid){

        int m = grid.length, n = grid[0].length;
        for(int i=1;i< m;i++)
        {
            grid[i][0] += grid[i-1][0];
        }

        for(int j=1;j<n;j++){
            grid[0][j] += grid[0][j-1];
        }

        for(int i=1;i< m;i++){
            for (int j=1;j<n;j++){
                grid[i][j] += Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }

        return grid[m-1][n-1];
    }





    private class SolutionRecursive {
        Integer minPathSum = Integer.MAX_VALUE;
        Boolean isEndReachedAtleastOnce = false;

 public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int pathSum = 0;

        if(m > 0 && n>0)
            findMinPathSum(grid,0,0,m,n,pathSum);

        return minPathSum;
    }

   private void findMinPathSum(int[][] A , int i,int j, int m, int n, int pathSum){
       System.out.println("i="+i+" j="+j);
       if( i >= m || j >= n)
           return;

       pathSum += A[i][j];

       if(pathSum >= minPathSum)
           if(this.isEndReachedAtleastOnce)
               return;

       findMinPathSum(A,i+1,j,m,n,pathSum);
       findMinPathSum(A,i,j+1,m,n,pathSum);

       if((i == m-1) && (j == n-1)){
           this.isEndReachedAtleastOnce = true;
           if(pathSum < minPathSum)
           {
               minPathSum = pathSum;
           }
           return;
       }
   }
    }



}


