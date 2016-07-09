package com.hackerrank.weekofcode21.luckbalance.lazysorting;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer,Integer> counter = new HashMap<>();
        for(int i=0;i<n;i++){
            int val = sc.nextInt();
            if(counter.containsKey(val)){
                Integer count =counter.get(val);
                count++;
                counter.put(val,count);

            } else {
                counter.put(val,1);
            }


        }

        long x=1;
        for(Integer val : counter.values()){
            x*=fac(val);
        }

        long nfac = fac(n);
        //if (x == nfac) {
          //  System.out.println("0.000000");

        //} else {
            double expec = (double) nfac / (double)x;
            //        System.out.println(String.format(".%6d",expec));
            System.out.format("%.6f%n", expec);
        //}
    }

    public static long fac(int n){
        long val =1;
        for(int i=1;i<=n;i++){
            val*=i;
        }
        return val;
    }
}
