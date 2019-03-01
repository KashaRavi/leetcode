package amazon.inordersuccessor;

/**
 * Created by rkasha on 1/26/19.
 */

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

public class TestClass {
    static Node head =null;

    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(2);

        buildLinkedList(root);

        while(head != null) {
            System.out.print(head.data +" ");
            head = head.succ;
        }
    }

    /**
     * recursively construct linked list for inorder traversal of nodes.
     * This can be done by reverse inorder traversal of the tree
     */
    public static void buildLinkedList(Node node) {
        if(node !=null) {
            buildLinkedList(node.right);
            node.succ = head;
            head = node;
            buildLinkedList(node.left);
        }
    }
}

