//https://www.geeksforgeeks.org/write-c-code-to-determine-if-two-trees-are-identical/
package datastructures.trees.identicaltrees;

import tree.Node;
import tree.TreeUtil;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {

    public static void main(String[] args) {
        Node<Integer> root1 = TreeUtil.getIntTree();
        Node<Integer> root2 = TreeUtil.getIntTree1();
        if(checkIfIdentical(root1, root2)) {
            System.out.println("Identical");
        } else {
            System.out.println("Not identical");
        }
    }

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
}
