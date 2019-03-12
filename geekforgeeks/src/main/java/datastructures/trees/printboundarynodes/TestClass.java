package datastructures.trees.printboundarynodes;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/9/19.
 */

//Note: this is reprinting the root node again part of right boundary
public class TestClass {
    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(6);
        ITreeUtil.printTree(root);
        printBoundaryNodes(root);
    }

    public static void printBoundaryNodes(Node<Integer> root) {
        printLeftBoundaryNodes(root);
        System.out.println();
        printleafNodes(root);
        System.out.println();
        printRightBoundaryNodes(root);
    }

    public static void printLeftBoundaryNodes(Node<Integer> root) {

        if (root != null && (root.left != null || root.right != null)) {
            System.out.print(root.data + " ");

            if (root.left != null) {
                printLeftBoundaryNodes(root.left);
            } else if (root.right != null) {
                printLeftBoundaryNodes(root.right);
            }
        }
    }

    public static void printRightBoundaryNodes(Node<Integer> root) {
        if (root != null) {
            if ((root.left != null || root.right != null))
                System.out.print(root.data + " ");
        }

        if (root.right != null) {
            printRightBoundaryNodes(root.right);
        } else if (root.left != null) {
            printRightBoundaryNodes(root.left);
        }
    }

    public static void printleafNodes(Node<Integer> root) {
        if (root != null) {
            if(root.left == null && root.right == null)
            System.out.print(root.data + " ");
            printleafNodes(root.left);
            printleafNodes(root.right);
        }
    }

}
