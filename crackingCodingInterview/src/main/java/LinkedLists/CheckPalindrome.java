package LinkedLists;

import java.util.Arrays;
import java.util.List;

/**
 * @author rkasha
 */
public class CheckPalindrome {
    public final class Result{
        Node front;
        Boolean result;
        
        Result(Node node,Boolean result){
            this.front=node;
            this.result=result;
            
        }
    }

    public static void main(String[] args){
        CheckPalindrome checkPalindrome =new CheckPalindrome(); 
//        checkPalindrome.test01();
//        checkPalindrome.test02();
        checkPalindrome.test03();

    }

    private void test01(){
        List<Integer> linkList = Arrays.asList(new Integer[] { 34, 36, 12, 14, 65, 12, 87, 98 });
        Node head = LinkedListUtil.createLinkedList(linkList);
        LinkedListUtil.printlinkedList(head);
        Result res = isPalindrome(head,head);
        if(res.result){
            System.out.println("\nIs a palindrome");
        }else {
            System.out.println("\nIs not a palindrome");

        }
    }

    private void test02(){
        List<Integer> linkList = Arrays.asList(new Integer[] { 12, 13, 14, 15, 14, 13, 12 });
        Node head = LinkedListUtil.createLinkedList(linkList);
        LinkedListUtil.printlinkedList(head);
        Result res = isPalindrome(head,head);
        if(res.result){
            System.out.println("\nIs a palindrome");
        }else {
            System.out.println("\nIs not a palindrome");

        }
    }

    private void test03(){
//        List<Integer> linkList = Arrays.asList(new Integer[] { 12, 13, 14, 14, 13, 12 });
        List<Integer> linkList = Arrays.asList(new Integer[] { 12, 13, 12, 12 });
        Node head = LinkedListUtil.createLinkedList(linkList);
        LinkedListUtil.printlinkedList(head);
        Result res = isPalindrome(head,head);
        if(res.result){
            System.out.println("\nIs a palindrome");
        }else {
            System.out.println("\nIs not a palindrome");

        }
    }

    public Result isPalindrome(Node front,Node back){
        if(back==null){
            return new Result(front,true);
        }
        
        Result res = isPalindrome(front,back.next);

        if(res.front.data == back.data){
            res.result= res.result && true;
        } else {
            res.result = false;
        }
        res.front = res.front.next;
        return res;
    }
}
