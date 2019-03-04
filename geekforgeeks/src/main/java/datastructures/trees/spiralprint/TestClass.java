//https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/
package datastructures.trees.spiralprint;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by rkasha on 3/4/19.
 */
public class TestClass {

    /**
     *  This can be solved using two stacks s1, s2.
     *  print the element while popping an element
     *  pop all the elements from s1. while popping each node from s1 push its left and right nodes
     *  into s2.
     *  Now start popping  all the nodes from s2. while popping push its right and left nodes into s1.
     *  Repeat the previous two steps till both the stacks become empty.
     *
     */
    public static void printSpiralTraversal(Stack<Node<Integer>> s1, Stack<Node<Integer>> s2) {
        while (!(s1.isEmpty() && s2.isEmpty())) {
            while (!s1.isEmpty()) {
                Node<Integer> node = s1.pop();
                if (node != null) {
                    System.out.print(node.data + "->");
                    s2.push(node.left);
                    s2.push(node.right);
                }
            }

            System.out.println();

            while (!s2.isEmpty()) {
                Node<Integer> node = s2.pop();
                if (node != null) {
                    System.out.print(node.data + "->");
                    s1.push(node.right);
                    s1.push(node.left);
                }
            }
            System.out.println();
        }
    }

    /* Driver program to test above functions */
    public static void main(String args[]) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(1);
        ITreeUtil.printTree(root);
        Stack<Node<Integer>> s1 = new Stack<Node<Integer>>();
        Stack<Node<Integer>> s2 = new Stack<Node<Integer>>();
        s1. push(root);
        printSpiralTraversal(s1, s2);
    }
}
