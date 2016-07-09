package com.topcoder;

/**
 * @author rkasha
 */
public class DoubleOrOneEasy {

    public static void main(String[] args){
        DoubleOrOneEasy doubleOrOneEasy = new DoubleOrOneEasy();
//        System.out.println(doubleOrOneEasy.minimalSteps(100, 1000, 101, 1001));
//        System.out.println(doubleOrOneEasy.minimalSteps(100, 1000, 202, 2002));
//        System.out.println(doubleOrOneEasy.minimalSteps(2, 2, 1, 1));
//        System.out.println(doubleOrOneEasy.minimalSteps(1,111111111, 8, 888888888));
//        System.out.println(doubleOrOneEasy.minimalSteps(1,111111111, 9, 999999999));
        System.out.println(doubleOrOneEasy.minimalSteps(1, 2, 999999999, 1000000000));

    }
    
    public int minimalSteps(int a, int b, int newA,int newB){
        if(a==newA && b==newB){
            return 0;
        } else if(a < newA && b < newB){
            int curSum = minimalSteps(a+1,b+1,newA,newB);
            int curProd = minimalSteps(a*2,b*2,newA,newB);
            if(curSum!=-1 && curProd!=-1){
                return Math.min(curSum,curProd)+1;
            } else if(curSum==-1 && curProd !=-1){
                return curProd+1;
            } else if(curSum!=-1 && curProd ==-1){
                return curSum+1;
            }
        }
        return -1;
    }
}


