package datastructures.trees.pathsum;

/**
 * Created by rkasha on 2/28/19.
 */
import java.util.Stack;

public class FindIfPathExists {

    private static Stack<Node> stack = new Stack();

    static class Node {
        int val;
        Node left;
        Node right;

        Node(int value) {
            this.val = value;
            right = null;
            left = null;
        }
    }

    public static void main(String[] args) {

        // Create Tree
        Node root = new Node(5);
        root.left = new Node(2);

        root.left.left = new Node(3);
        root.left.right = new Node(13);

        root.left.left.left = new Node(7);
        root.left.left.right = new Node(7);

        int sum = 20;
        if (isPathExisting(root, sum)) {
            while (!stack.isEmpty()) {
                System.out.print(stack.pop().val+"->");
            }
        } else {
            System.out.println("No path Exists");
        }
    }

    private static boolean isPathExisting(Node root, int sum) {
         if (root == null) {
            return false;
        }

        if (root.right == null && root.left == null && sum-root.val == 0) {
            stack.push(root);
            return true;
        }

        if (isPathExisting(root.left, sum-root.val)) {
            stack.push(root);
            return true;
        }

        if (isPathExisting(root.right, sum-root.val)) {
            stack.push(root);
            return true;
        }

        return false;

    }
}