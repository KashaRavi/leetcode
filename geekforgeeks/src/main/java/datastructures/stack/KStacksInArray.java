package datastructures.stack;

import datastructures.Arrays.ArrayUtils;

import java.util.Stack;

/**
 * Created by rkasha on 3/30/19.
 */
public class KStacksInArray {
    public static void main(String[] args) {
        KStacksInArray stacks = new KStacksInArray();
        stacks.initialize(4, 20);
        ArrayUtils.printArray(stacks.arr);
        stacks.push(1, 1);
        stacks.push(1, 2);
        stacks.push(1, 3);

        stacks.push(0, 4);
        stacks.push(0, 5);

        stacks.push(2, 6);

        stacks.pop(1);
        stacks.pop(0);
        stacks.push(1, 3);
        stacks.pop(2);
        stacks.pop(2);
        stacks.pop(3);



        ArrayUtils.printArray(stacks.arr);
        ArrayUtils.printArray(stacks.next);

    }

     int k;
     int n;
     int[] top;
     int[] arr;
     int[] next;
     int freeIndex;

    //each stack is given a number from o to k-1;
    //maintain top for every stack


    public void initialize(int k1, int n1){

        k=k1;
        n=n1;
        top = new int[k];
        for(int i=0;i<k;i++){
            top[i]=-1;
        }
        arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=-1;
        }
        next = new int[n];
        for(int i=0;i<n;i++){
            next[i]=i+1;
        }
        next[n-1]=-1;
        freeIndex =0;
    }

    public void push(int stackNum, int val){
        if(freeIndex==-1){
            System.out.println("Stack is full");
            return;
        }

        int i=freeIndex;
        freeIndex=next[i];

        next[i]=top[stackNum];
        arr[i]=val;
        top[stackNum]=i;
    }

    public int pop(int stackNum){
        if(top[stackNum]==-1){
            System.out.println("stack:"+stackNum+"is empty");
            return -1;
        }

        int i= top[stackNum];

        top[stackNum]=next[i];
        next[i]=freeIndex;
        freeIndex=i;
        int val= arr[i];
        arr[i]=-1;
        return val;
    }
}
