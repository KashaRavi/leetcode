package com.codechef.chefsRedBlackTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by rkasha on 11/15/2014.
 */
public class Main {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int Q = Integer.parseInt(br.readLine());
            String rootColor = "black";
            for(int i=0;i<Q;i++){
                String query = br.readLine();
                String[] commands = query.split(" ");
                if(commands[0].equals("Qi")){
                    rootColor = rootColor.equals("black")?"red":"black";
                } else {
                    int x = Integer.parseInt(commands[1]),y= Integer.parseInt(commands[2]),xlevel=0,ylevel=0;
                    Stack<Integer> xStack = new Stack<Integer>();
                    Stack<Integer> yStack = new Stack<Integer>();

                    while(x >0){
                        xStack.push(x);
                        xlevel++;
                        x /= 2;
                    }

                    while(y > 0)
                    {
                        yStack.push(y);
                        ylevel++;
                        y /= 2;
                    }

                    int currentNode = 1;
                    int commonAncestor = 1;
                    int commonAncestorLevel = 0;
                    while( !xStack.isEmpty() && (currentNode=xStack.pop()) == yStack.pop()){
                        commonAncestor = currentNode;
                        commonAncestorLevel++;
                    }

                    if(commands[0].equals("Qb")){
                        if(rootColor.equals("black")){
                            System.out.println(computeNodes(xlevel,ylevel,commonAncestorLevel,true));
                        } else {
                            System.out.println(computeNodes(xlevel,ylevel,commonAncestorLevel,false));
                        }
                    } else {
                        if(rootColor.equals("black")){
                            System.out.println(computeNodes(xlevel,ylevel,commonAncestorLevel,false));
                        } else {
                            System.out.println(computeNodes(xlevel,ylevel,commonAncestorLevel,true));
                        }
                    }
                }
            }
        }catch(IOException e){

        }
    }

    private static int computeNodes(int xHeight,int yHeight,int ancestorHeight,boolean matchingWithRoot){
        int numNodes = 0;
        if(matchingWithRoot) {
             numNodes = (xHeight/2 + xHeight%2) + (yHeight/2 + yHeight%2) - 2 * (ancestorHeight/2 + ancestorHeight%2);
            numNodes += (ancestorHeight%2 == 1) ? 1 : 0;
        } else {
            numNodes = xHeight/2 + yHeight/2 - 2*(ancestorHeight/2);
            numNodes += ancestorHeight%2 == 0?1:0;
        }
        return numNodes;
    }
}
