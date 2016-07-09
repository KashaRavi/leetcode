package com.crackingCodingInterview.trees;

import java.util.List;

/**
 * @author rkasha
 */
public class BinaryTreeBuilder {

    public Node buildBinaryTree(List<Integer> arr,int first, int last){
        
        if(last < first){
            return null;
        }
        
        int mid = (first+last)/2;
        
        Node root = new Node(arr.get(mid));
        root.left = buildBinaryTree(arr,first,mid-1);
        root.right = buildBinaryTree(arr,mid+1,last);
        
        return root;
    }
    
    
}
