package datastructures.trees.countleafnodes;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/4/19.
 */
public class TestClass {
    static int getLeafCount(Node<Integer> node)
    {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;
        else
            return getLeafCount(node.left) + getLeafCount(node.right);
    }

    /* Driver program to test above functions */
    public static void main(String args[]) throws IOException {
        Node<Integer>  root = ITreeUtil.getTree(2);
        ITreeUtil.printTree(root);
        /* get leaf count of the abve tree */
        System.out.println("The leaf count of binary tree is : "
                + getLeafCount(root));
    }
}
