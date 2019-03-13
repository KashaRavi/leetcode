package datastructures.stack;

import java.util.Iterator;
import java.util.Stack;

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
}
