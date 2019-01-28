package amazon.inordersuccessor;

/**
 * Created by rkasha on 1/26/19.
 */

import tree.BTreePrinter;
import tree.Node;
public class TestClass {

}

// Java program for different tree traversals

/* Class containing left and right child of current
node and data value*/
class BinaryTree
{
    // Root of Binary Tree
    Node root;

    BinaryTree()
    {
        root = null;
    }

    /* Given a binary tree, print its nodes according to the
    "bottom-up" postorder traversal. */
    void printPostorder(Node node)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.data + " ");
    }

    /* Given a binary tree, print its nodes in inorder*/
    void printInorder(Node node)
    {
        if (node == null)
            return;

		/* first recur on left child */
        printInorder(node.left);

		/* then print the data of node */
        System.out.print(node.data + " ");

		/* now recur on right child */
        printInorder(node.right);
    }

    /* Given a binary tree, print its nodes in preorder*/
    void printPreorder(Node node)
    {
        if (node == null)
            return;

		/* first print data of node */
        System.out.print(node.data + " ");

		/* then recur on left sutree */
        printPreorder(node.left);

		/* now recur on right subtree */
        printPreorder(node.right);
    }

    // Wrappers over above recursive functions
    void printPostorder() {	 printPostorder(root); }
    void printInorder() {	 printInorder(root); }
    void printPreorder() {	 printPreorder(root); }

    // Driver method
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        Node node = tree.root.right;
        node.left = new Node(6);
        node.right = new Node(7);
        Node tempNode = node;
        node = node.left;
        node.left = new Node(8);
        node.right = new Node(9);
        node = tempNode.right;
        node.left = new Node(0);
        node.right = new Node(1);

        /*

       1
      / \
     /   \
    /     \
   /       \
   2       3
  / \     / \
 /   \   /   \
 4   5   6   7
        / \ / \
        8 9 0 1

         */

//        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder();

        System.out.println();
//        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();

        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder();

        System.out.println();
        BTreePrinter.printNode(tree.root);
    }
}

