package com.hackerearth.rhezonquery;

/**
 * Created by rkasha on 12-Jun-16.
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/

import java.util.Scanner;

class TestClass {
    public static void main(String args[] ) throws Exception {
        /*
         * Read input from stdin and provide input before running

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int N = Integer.parseInt(line);
        for (int i = 0; i < N; i++) {
            System.out.println("hello world");
        }
        */

        //System.out.println("Hello World!");
        final int cons = 1000000007;
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[] a = new int[n];
        for(int i=0;i< n;i++){
            a[i]=sc.nextInt();
        }
        int q = sc.nextInt();
        for(int i=0;i<q;i++){
            int l=sc.nextInt();
            int r=sc.nextInt();
            int f=1;
            int s =0;
            int t=0;
            l--;
            r--;
            int j=l;
            while(j<=r){

                f= (f*a[j])%cons;
                j++;

                if(j<=r){
                    s = (s+a[j])%cons;
                    j++;
                }
                if(j <=r){
                    t = (t+a[j])%cons;
                    j++;
                }
            }
            int tot = (f+s)%cons;
            tot = (tot-t)%cons;
            System.out.println(tot);
        }
    }
}

