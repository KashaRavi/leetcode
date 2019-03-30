package datastructures.stack;

import java.util.Stack;

/**
 * Created by rkasha on 3/30/19.
 */
public class ReverseStackUsingRecursion {

    public static void main(String[] args) {
        int arr[] ={1, 2, 3, 4, 5, 6};
        Stack stack = StackUtils.getStack(arr);
        StackUtils.printStack(stack);
        System.out.println();
        reverseStack(stack);
        StackUtils.printStack(stack);
    }

    public static void reverseStack(Stack<Integer> stack){
        if(stack.isEmpty())
            return;

        int a = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack,a);
    }

    public static void insertAtBottom(Stack<Integer> stack, int a){
        if(stack.isEmpty()) {
            stack.push(a);
            return;
        }

        int top= stack.pop();
        insertAtBottom(stack, a);
        stack.push(top);
    }
}
