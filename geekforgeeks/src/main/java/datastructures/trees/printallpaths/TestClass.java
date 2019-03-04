package datastructures.trees.printallpaths;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

/**
 * Created by rkasha on 3/4/19.
 */
public class TestClass {

    static Stack<Integer> stack = new Stack<Integer>();

    /*
        It is basically a preorder traversal. push the node before visiting its children. Before
        the node before it returns so that its parent can try its other branch.

     */
    public static void printAllPaths(Node<Integer> root) {
        if(root == null)
            return;

        stack.push(root.data);
        printAllPaths(root.left);
        printAllPaths(root.right);
        if(root.left == null && root.right == null) {
            printStack(stack);
        }
        stack.pop();
    }

    public static void printStack(Stack<Integer> stack) {
        Iterator<Integer> iterator = stack.iterator();
        while(iterator.hasNext()) {
            System.out.print(iterator.next()+"->");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(2);
        ITreeUtil.printTree(root);
        printAllPaths(root);
    }
}
