//https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
package datastructures.trees.mirrorimage;

import tree.Node;
import tree.TreeUtil;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {
    public static void main(String[] args) {
        Node<Integer> root = TreeUtil.getIntTree();
        TreeUtil.printPostorder(root);
        System.out.println();
        constructMirrorImage(root);
        TreeUtil.printPostorder(root);
    }

    public static void constructMirrorImage(Node<Integer> root) {
        if(root == null) return;

        constructMirrorImage(root.left);
        constructMirrorImage(root.right);

        //        root.data = -1;
        Node<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
        return;
    }
}
