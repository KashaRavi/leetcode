package algorithms.dynamicprogramming.subsetsum;

import datastructures.stack.StackUtils;
import tree.ITreeUtil;
import tree.Node;

import java.util.Stack;

import static datastructures.trees.printallpaths.TestClass.printAllPaths;

/**
 * Created by rkasha on 3/12/19.
 */
public class TestClass {
    public static void main(String[] args) {
        int n = 9;
        System.out.println();
        int[] arr = {3, 34, 4, 12, 5, 2, 11, 6, 5};
        int sum = 10;
//        System.out.println(String.valueOf(hasSubSetSum(arr, sum, arr.length-1)));
        Node<Integer> root = new Node<Integer>(-1);
        System.out.println(String.valueOf(hasSubSetSum(arr, sum, arr.length-1, root) ));
//        ITreeUtil.printTree(root, true);
        printAllPaths(root);

    }

    static Stack<Integer> subset = new Stack<>();
    static boolean hasSubSetSum(int[] arr, int sum, int index) {

        if(sum==0) {
            StackUtils.printStack(subset);
            return true;
        }

        if(index < 0 && sum != 0) {
            return false;
        }

        boolean result = hasSubSetSum(arr, sum, index-1);
        if(!result) {
            subset.push(arr[index]);
            result = hasSubSetSum(arr, sum - arr[index], index-1);
            subset.pop();
        }

        return result;
    }

    static boolean hasSubSetSum(int[] arr, int sum, int index, Node<Integer> root) {

        if(sum==0) {
            StackUtils.printStack(subset);
            return true;
        }

        if(index < 0 && sum != 0) {
            return false;
        }


        boolean lResult = hasSubSetSum(arr, sum, index-1, root);
        boolean rResult;
        if(!lResult) {
            root.left = new Node<>(arr[index]);
            //if left child has subset then attempt to set the current element as left child
            rResult = hasSubSetSum(arr, sum - arr[index], index-1, root.left);
            if(!rResult){
                root.left = null;
            }
            root.right = null;
        } else {
            root.right = new Node<>(arr[index]);
            rResult = hasSubSetSum(arr, sum - arr[index], index-1, root.right);
            if(!rResult){
                root.right = null;
            }
            root.left = null;
        }

        return lResult || rResult ;
    }
}
