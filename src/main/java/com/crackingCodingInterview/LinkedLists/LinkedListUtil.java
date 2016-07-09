package com.crackingCodingInterview.LinkedLists;

import java.util.List;

/**
 * @author rkasha
 */
public class LinkedListUtil {

    public static Node createLinkedList(List<Integer> listElements){
        Node head=null,runner = null;
        for(Integer i:listElements){
            Node currNode = new Node(i);
            if(head==null){
                head=currNode;
                runner=head;
            } else{
                runner.next=currNode;
                runner = runner.next;
            }
        }
        return head;
    }
    
    public static void printlinkedList(Node head){
        if(head==null){
            System.out.println("Linked List is null");
        }
        while(head !=null){
            System.out.print("-->"+head.data);
            head=head.next;
        }
    }
}
