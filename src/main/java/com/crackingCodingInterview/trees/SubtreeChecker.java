package com.crackingCodingInterview.trees;

import java.util.Arrays;
import java.util.List;

/**
 * @author rkasha
 */
public class SubtreeChecker {
    public static void main(String[] args) {
        SubtreeChecker subtreeChecker = new SubtreeChecker();
        BinaryTreeBuilder binaryTreeBuilder = new BinaryTreeBuilder();
        //        Integer[] a = new Integer[]{1,4,6,23,54,11,6,3,7,8,6,9,3,0};
        //                Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
        // 13,24,15,67,23,22,16,7,55,45,78,22,90 };
        //                Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 
        // 13 };
        //        Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7 };
        Integer[] a = new Integer[] { 10, 20, 30, 40, 50, 60, 70, 40, 50, 70, 50, 20, 70, 80, 90,
              10, 30, 80, 50, 30, 90, 20, 40, 60, 20, 60, 70, 80, 90, 00, 10, 20, 30, 40, 20, 30,
              40 };
        //        Integer[] a = new Integer[] { 1, 2, 3, 4, 5, 6, 7,4,5,7,5,2,7,8,9,1,3,8,5,3,9,
        // 2,4,6,2,6,7,8,9,0,1,2,3,4,2,3,4 };
        //        Integer[] a = new Integer[]{13,13,13,13};
        //        Integer[] b = new Integer[] { 13, 13, 13, 13 };
        Integer[] b = new Integer[] { 1, 2, 3 };

        List<Integer> lista = Arrays.asList(a);
        List<Integer> listb = Arrays.asList(b);
        Node roota = binaryTreeBuilder.buildBinaryTree(lista, 0, lista.size() - 1);
        BTreePrinter.printNode(roota);
        Node rootb = binaryTreeBuilder.buildBinaryTree(listb, 0, listb.size() - 1);
        if (subtreeChecker.isSubTree(roota, rootb)) {
            System.out.println("rootb is a subtree of roota");
        } else {
            System.out.println("rootb is not a subtree of roota");

        }
    }

    private boolean isSubTree(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }

        return checkIfSubTree(t1, t2);

    }

    private boolean checkIfSubTree(Node t1, Node t2) {
        if (t1 == null)
            return false;

        if (t1.val == t2.val) {
            if (checkIfIsomorphic(t1, t2)) {
                return true;
            }
        }

        return checkIfSubTree(t1.left, t2) || checkIfSubTree(t1.right, t2);
    }

    private boolean checkIfIsomorphic(Node t1, Node t2) {

        if (t1 == null && t2 == null) {
            return true;
        }

        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }

        return checkIfIsomorphic(t1.left, t2.left) && checkIfIsomorphic(t1.right, t2.right);

    }
}
