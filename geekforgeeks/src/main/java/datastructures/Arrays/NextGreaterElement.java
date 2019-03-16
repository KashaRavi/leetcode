//https://www.geeksforgeeks.org/next-greater-element/
package datastructures.Arrays;

import java.util.Stack;

/**
 * Created by rkasha on 3/16/19.
 */
public class NextGreaterElement {
    public static void main(String[] args)
    {
//        int arr[] = {1, 2, 3, 1, 3, 6, 6};
//        int arr[] = {4,5,2,25};
        int arr[] = {11,13,21,3};
        int[] res = findNextGreaterElement(arr);
        ArrayUtils.printArray(res);
    }

    /**
     *
     * Solution is O(n) with extra space. The current implementation uses stack.
     * Note this problem is similar to the stack span problem @ https://www.geeksforgeeks.org/the-stock-span-problem/
     */
    public static int[] findNextGreaterElement(int[] arr){
        Stack<Integer> stack = new Stack<>();
        int n= arr.length;
        stack.push(arr[n-1]);
        int[] output = new int[n];
        output[n-1]=-1;

        for (int i = n-2; i >=0 ; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]){
                stack.pop();
            }
            if(!stack.isEmpty()){
                output[i]=stack.peek();
            } else {
                output[i]=-1;
            }
            stack.push(arr[i]);
        }
        return output;
    }
}
