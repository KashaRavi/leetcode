package datastructures.linkedlist;

import tree.Node;

/**
 * Created by rkasha on 3/4/19.
 */
public class LLUtil {
    public static void printLinkedList(Node<Integer> head) {
        Node<Integer> temp = head;
        while(temp!= null) {
            System.out.println(temp.data+"->");
            temp = temp.right;
        }
    }

    public static void printLinkedList(Node<Integer> head, Node<Integer> node) {
        Node<Integer> temp = head;
        while(temp!= node) {
            System.out.println(temp.data+"->");
            temp = temp.right;
        }
    }
}
