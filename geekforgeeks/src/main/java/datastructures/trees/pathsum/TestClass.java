//https://www.geeksforgeeks.org/root-to-leaf-path-sum-equal-to-a-given-number/

package datastructures.trees.pathsum;

import tree.TreeUtil;
import tree.Node;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by rkasha on 2/28/19.
 */
public class TestClass {


    public static Stack<Integer> stack = new Stack<Integer>();

    public static void main(String[] args) throws IOException {

        Node<Integer> root = TreeUtil.getIntTree();

//        printPath(root, 18);
        printPath(root, 11);
//
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() +"->");
        }
    }

    public static boolean printPath(Node<Integer> root, int sum) {
        if(root == null) {
            return false;
        }

        int val = root.data;
        if(root.left == null && root.right ==null && sum - val == 0) {
            stack.push(val);
            return true;
        }

        if(printPath(root.left, sum-val)) {
            stack.push(val);
            return true;
        }
        if(printPath(root.right, sum-val)) {
            stack.push(val);
            return true;
        }

        return false;
    }

}
