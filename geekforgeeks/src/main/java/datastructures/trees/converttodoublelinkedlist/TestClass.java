//https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
package datastructures.trees.converttodoublelinkedlist;

import datastructures.linkedlist.LLUtil;
import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/4/19.
 */
public class TestClass {

    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(2);
        ITreeUtil.printTree(root);
        ITreeUtil.printInorder(root);
        convertToCircularDoubleLinkedList(root);
        head.left = tail;
        tail.right = head;
        LLUtil.printLinkedList(head, tail);
    }

    /**
     * To either construct a linkedList or doubly linked we need to do reverse inorder traversal
     * of a tree.
     */
    static Node<Integer> head = null;
    static Node<Integer> tail = null;
    public static void convertToCircularDoubleLinkedList(Node<Integer> root) {
        if (root == null)
            return;

        convertToCircularDoubleLinkedList(root.right);
        root.right = head;
        if (head != null) {
            head.left = root;
        }
        if(head == null) {
            tail = root;
        }
        head = root;
        convertToCircularDoubleLinkedList(root.left);
    }
}
