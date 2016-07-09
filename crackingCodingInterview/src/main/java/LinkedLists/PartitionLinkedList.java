package LinkedLists;

import java.util.Arrays;
import java.util.List;

/**
 * @author rkasha
 */
public class PartitionLinkedList {
    
    public static void main(String[] args){
        List<Integer> linkList = Arrays.asList(new Integer[]{34,36,12,14,65,12,87,98});
        Node head = LinkedListUtil.createLinkedList(linkList);
        head = new PartitionLinkedList().partitionlinkedList(head,15);
        LinkedListUtil.printlinkedList(head);
    }
    
    private Node partitionlinkedList(Node head,int x){
        if(head==null)
              return null;
        
        Node runner = head;
        while(runner.next != null){
            if(runner.next.data < x){
                Node temp = runner.next;
                runner.next= temp.next;
                temp.next=head;
                head=temp;
            } else{
                runner=runner.next;
            }
        }
        return head;
    }
}
