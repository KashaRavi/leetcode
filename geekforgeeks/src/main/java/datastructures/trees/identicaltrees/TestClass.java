//https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
package datastructures.trees.identicaltrees;

import tree.Node;
import tree.ITreeUtil;

import java.io.IOException;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {

    private static  boolean checkIfIdentical(Node<Integer> root1, Node<Integer> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        }

        if (root1.data == root2.data) {
            return checkIfIdentical(root1.left, root2.left) && checkIfIdentical(root1.right,
                    root2.right);
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        Node<Integer> root1 = ITreeUtil.getTree(1);
        Node<Integer> root2 = ITreeUtil.getTree(2);
        ITreeUtil.printTree(1);
        ITreeUtil.printTree(2);

        if(checkIfIdentical(root1, root2)) {
            System.out.println("Identical");
        } else {
            System.out.println("Not identical");
        }
    }
}
