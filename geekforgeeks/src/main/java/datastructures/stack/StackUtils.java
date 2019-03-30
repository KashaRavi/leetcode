package datastructures.stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import static java.util.Arrays.*;

/**
 * Created by rkasha on 3/12/19.
 */
public class StackUtils {

    public static void printStack(Stack<Integer> stack) {
        Iterator itr = stack.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    public static Stack<Integer> getStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int v : arr) {
            stack.push(v);
        }
        return stack;
    }
}
