package com.codechef.segmentGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rkasha on 11/15/2014.
 */
public class Main {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int T = Integer.parseInt(br.readLine());
            double k=0,currStep=0,tempStep=0;
            double x =0,prevSegLen=0,currSegLen=0,nextMidPoint=0,prevNumSegments=0;
            String line = null;
            String[] input = null;
            for(int i=0;i<T;i++){
                line = br.readLine();
                input = line.split(" ");
                x= Double.parseDouble(input[0]);
                k =Double.parseDouble(input[1]);
                 prevSegLen = x;
                 currSegLen = x;
                 nextMidPoint = x;
                 currStep = 0;tempStep=0;
                prevNumSegments =1;
                while(currStep < k){
                    currSegLen = nextMidPoint = prevSegLen/2;
                    tempStep += prevNumSegments;
                    if(tempStep >= k){
                        System.out.println(nextMidPoint+(k-currStep-1)*prevSegLen);
                        break;

                    } else {
                        currStep = tempStep;
                        prevNumSegments *= 2;
                    }
                    prevSegLen = currSegLen;
                }
            }
        }catch (IOException e){

        }
    }
}
