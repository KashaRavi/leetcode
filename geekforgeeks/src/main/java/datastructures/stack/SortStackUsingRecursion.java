package datastructures.stack;

import java.util.Stack;

/**
 * Created by rkasha on 3/30/19.
 */
public class SortStackUsingRecursion {
    public static void main(String[] args) {
//        int arr[] ={3, 2,2, 1, 7,7,7, 5, 6};
        int arr[] ={7,6, 5};
        Stack stack = StackUtils.getStack(arr);
        StackUtils.printStack(stack);
        System.out.println();
        sortStack(stack);
        StackUtils.printStack(stack);
    }

    public static void sortStack(Stack<Integer> stack){
        if(stack.isEmpty()) return;

        int top = stack.pop();
        sortStack(stack);
        insertAtRightPlace(stack, top);
    }

    public static void insertAtRightPlace(Stack<Integer> stack, int val){

        if(stack.isEmpty())
        {
            stack.push(val);
            return;
        }

        int top = stack.peek();
        if(val> top){
            stack.push(val);
        } else {
            stack.pop();
            insertAtRightPlace(stack,val);
            stack.push(top);
        }
    }
}
