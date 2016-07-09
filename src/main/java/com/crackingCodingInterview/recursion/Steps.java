package com.crackingCodingInterview.recursion;

import java.util.Arrays;

/**
 * @author rkasha
 */
public class Steps {
    
    public static void main(String[] args){
        Steps steps= new Steps();
        System.out.println(steps.countSteps(1));
        System.out.println(steps.countSteps(2));
        System.out.println(steps.countSteps(3));
        System.out.println(steps.countSteps(4));
        System.out.println(steps.countSteps(5));
        System.out.println(steps.countSteps(6));
        System.out.println(steps.countSteps(7));
        System.out.println(steps.countSteps(8));
        int[] a = new int[10];
        Arrays.fill(a,-1);
        System.out.println("--------------");
        System.out.println(countWaysDP(1,a));
        System.out.println(countWaysDP(2,a));
        System.out.println(countWaysDP(3,a));
        System.out.println(countWaysDP(4,a));
        System.out.println(countWaysDP(5,a));
        System.out.println(countWaysDP(6,a));
        System.out.println(countWaysDP(7,a));
        System.out.println(countWaysDP(8,a));

        
    }
    
    private int countSteps(int n){
        if(n<4) return n;
        int a=1,b=2,c=3,count=1;
        for(int i=4;i<=n;i++){
                   count=a+b+c;
                    a=b;
                    b=c;
                    c=count;
            
        }
        return count;
    }

     public static int countWaysDP(int n, int[] map) {
      if (n < 0) {
      return 0;
      } else if (n == 0) {
      return 1;
      } else if (map[n] > -1) {
      return map[n];
      } else {
      map[n] = countWaysDP(n - 1, map) +
      countWaysDP(n - 2, map) +
      countWaysDP(n - 3, map);
            return map[n];
      }
      }
}
