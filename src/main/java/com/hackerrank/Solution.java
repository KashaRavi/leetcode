package com.hackerrank;

/**
 * @author rkasha
 */
import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        long cons = 1000000007;
        for(int i=0;i<t;i++){
            long n=sc.nextLong();
            long m = sc.nextLong();

            if (n == 1) {
                System.out.println(m);
            } else if(n==2){
                System.out.println((m*(m-1))%cons);

            } else {
                long temp = findExponentBySquaring(m-2,n-2,cons);
                m = (m*(m-1))%cons;
                System.out.println((m*temp)%cons);
            }
        }
    }

    public static long findExponentBySquaring(long m,long n,long cons){

        long y=1;
        while(n >1) {
            if (n % 2 == 0) {
                m = (m * m)%cons;
                n = n / 2;
            } else {
                y = (m*y)%cons;
                m = (m*m)%cons;
                n = (n-1)/2;
            }
        }
        return (m*y)%cons;

    }
}