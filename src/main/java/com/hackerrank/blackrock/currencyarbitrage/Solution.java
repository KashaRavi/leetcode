package com.hackerrank.blackrock.currencyarbitrage;

import java.util.Scanner;

/**
 * Created by rkasha on 12-Jun-16.
 */
public class Solution {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int initial = 100000;
        for(int i=0; i<n; i++){
            double euroConv = sc.nextDouble();
            double gbpConv = sc.nextDouble();
            double usdConv = sc.nextDouble();
            double netprofit = 100000/(euroConv*gbpConv*usdConv) - 100000;
            if(netprofit >0){
                System.out.println((int)netprofit);
            } else {
                System.out.println(0);
            }

        }
    }


}
