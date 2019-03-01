//https://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
package datastructures.trees.mirrorimage;

import tree.BTreePrinter;
import tree.Node;
import tree.ITreeUtil;

import java.io.IOException;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {

    public static void constructMirrorImage(Node<Integer> root) {
        if(root == null) return;

        constructMirrorImage(root.left);
        constructMirrorImage(root.right);

        Node<Integer> temp = root.left;
        root.left = root.right;
        root.right = temp;
        return;
    }

    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(0);

        ITreeUtil.printTree(0);
        constructMirrorImage(root);
        ITreeUtil.printTree(root);
    }
}
