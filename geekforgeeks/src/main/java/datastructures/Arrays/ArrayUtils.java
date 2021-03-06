package datastructures.Arrays;

import java.util.ArrayList;

/**
 * Created by rkasha on 3/13/19.
 */
public class ArrayUtils {

    public static void printArray(int[] arr) {
        for(int val : arr) {
            System.out.print(val+" ");
        }
        System.out.println();
    }

    public static void printArray(ArrayList<Integer> arr) {
        for(int val : arr) {
            System.out.print(val+" ");
        }
        System.out.println();
    }

    public static void printArray(Integer[] arr) {
        for(int val : arr) {
            System.out.print(val+" ");
        }
        System.out.println();
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i]= arr[j];
        arr[j]=tmp;
    }
}
