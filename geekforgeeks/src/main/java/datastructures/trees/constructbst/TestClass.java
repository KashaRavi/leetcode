//https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/
package datastructures.trees.constructbst;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/6/19.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
//        int[] arr = { 1, 1, 2, 3, 3, 4, 9, 10 };
        int[] inorderArr = {1, 2, 3, 4};
        Node<Integer> root = constructBalancedBST(inorderArr, 0, inorderArr.length-1);
        ITreeUtil.printTree(root);
        ITreeUtil.printInorder(root);
    }

    public static Node<Integer> constructBalancedBST(int[] arr, int start, int end) {
        if(start> end)
            return null;
        int mid = (start + end)/2;
        Node<Integer> root = new Node<>(arr[mid]);
        root.left = constructBalancedBST(arr, start, mid-1);
        root.right = constructBalancedBST(arr, mid+1, end);
        return root;
    }
}
