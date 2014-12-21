package com.leetcode.pathSum;

/**
 * Created by rkasha on 10/28/2014.
 */
public class Solution {

    // Definition for binary tree
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
      }


    public boolean hasPathSum(TreeNode root, int sum) {

        if(root == null ) {
            return false;
        }

        if (root.left == null && root.right == null){
            if(sum-root.val == 0)
                return true;
            return false;
        }

        return hasPathSum(root.left,sum-root.val) || hasPathSum(root.right,sum-root.val);


    }


}
