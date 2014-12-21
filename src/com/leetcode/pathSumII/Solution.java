package com.leetcode.pathSumII;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by rkasha on 10/28/2014.
 */
public class Solution {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new LinkedList<List<Integer>>();
        if(root != null)
        {
            int[] stk = new int[100];
            getPaths(paths,root,sum,stk,0);
        }

        return paths;
    }

    void getPaths(List<List<Integer>> paths , TreeNode root,int sum,int[] stk, int stkLen){

        if(root == null)
            return;

        stk[stkLen] = root.val;
        stkLen++;

        if(root.left == null && root.right == null)
        {
            if(sum-root.val == 0)
                paths.add(buildList(stk,stkLen));

        }
        getPaths(paths,root.left,sum-root.val,stk,stkLen);
        getPaths(paths,root.right,sum-root.val,stk,stkLen);
    }

    List<Integer> buildList(int[] stack,int len){
        List<Integer> path = new LinkedList<Integer>();
        for (int i=0;i<len;i++){
            path.add(stack[i]);
        }
        return  path;
    }
}
