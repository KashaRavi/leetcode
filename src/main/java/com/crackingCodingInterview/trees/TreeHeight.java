package com.crackingCodingInterview.trees;

/**
 * @author rkasha
 */
public class TreeHeight {
    public static int findHeight(Node root){
        if(root == null){
            return 0;
        }
        return 1+ Math.max(findHeight(root.left),findHeight(root.right));
    }
}
