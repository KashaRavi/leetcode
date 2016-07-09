package com.crackingCodingInterview.trees;

import java.util.Arrays;
import java.util.List;

/**
 * @author rkasha
 */
public class PathSum {
    public static void main(String[] args){
        PathSum pathSum = new PathSum();
        BinaryTreeBuilder binaryTreeBuilder = new BinaryTreeBuilder();
//        Integer[] arr = new Integer[] { 10, 20, 30, 40, 50, 60, 70, 40, 50, 70, 50, 20, 70, 80, 90,
//              10, 30, 80, 50, 30, 90, 20, 40, 60, 20, 60, 70, 80, 90, 00, 10, 20, 30, 40, 20, 30,
//              40 };
        Integer[] arr = new Integer[] { 1, 2, 3, 4, 5, 6, 7,4,5,7,5,2,7,8,9,1,3,8,5,3,9,
              2,4,6,2,6,7,8,9,0,1,2,3,4,2,3,4 };
        List<Integer> list = Arrays.asList(arr);
        Node root = binaryTreeBuilder.buildBinaryTree(list, 0, list.size() - 1);
        BTreePrinter.printNode(root);
        int level = TreeHeight.findHeight(root);
        Integer[] a = new Integer[level];
        pathSum.findSum(a,root,9,0);
    }
    
    public void findSum(Integer[] a, Node root, int sum,int level){
        if(root == null){
            return;
        }
        a[level]=root.val;
        
        int s=0;
        for(int i=level;i>=0;i--){
            s+=a[i];
            if(s==sum){
                print(a,i,level);
            }
        }
        
        findSum(a,root.left,sum,level+1);
        findSum(a,root.right,sum,level+1);
    }
    
    public void print(Integer[] a,int start,int end){
        for(int i=start;i<=end;i++){
            System.out.print(a[i]+" ");
        }
        System.out.println();
        
    }
}
