//https://www.geeksforgeeks.org/write-a-c-program-to-delete-a-tree/
package datastructures.trees.deletetree;

import tree.Node;
import tree.TreeUtil;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {
    public static void main(String[] args) {
        Node<Integer> root = TreeUtil.getIntTree();
        deleteTree(root);
        root = null;
        TreeUtil.printPostorder(root);
    }

    public static void deleteTree(Node<Integer> root) {
        if(root == null) return;

        deleteTree(root.left);
        deleteTree(root.right);

//        root.data = -1;
        root.left = null;
        root.right = null;
        return;
    }
}
