package findMatrices;

import java.util.Arrays;
import java.util.Random;

/**
 * @author rkasha
 */
public class Solution {
    public static void main(String args[]){
        int min = 4,max=10;
        int n=10;
        Integer[] a = new Integer[n];
        
        for(int i=0;i<n;i++){
         a[i] = randInt(min,max);   
        }

        Arrays.sort(a);
        
        Solution sol = new Solution();
        sol.findNumSquares(n,a,min,max);
        
        
        
        
        
        
    }
    
    public Integer[] findPairs(Integer[] a,int max,int n)
    {
        Integer[] b= new Integer[2*max+1];
        for(int i=0;i<n;i++){
            for(int j=i+1;i<n;j++){
                b[a[i]+a[j]]++;
            }
        }
        return b;
        
    }
    
    public Integer[] findTriplets(Integer[] a,int n,int min,int max,Integer[] pairSumArray,Integer[] singletArray){
        
        Integer[] b= new Integer[max+1];
        for(int tripletSum=min;tripletSum<=max;tripletSum++){
            for(int j=0;j<n;j++){
            int currVal = a[j];
                int requiredPairSum = tripletSum-currVal;
                int totalPairsWithRequiredPairSum = pairSumArray[requiredPairSum];
                int totalPairsWhichAlreadyIncludeCurrentStick = singletArray[requiredPairSum-currVal];
                int numInvalidPairsWhichAlreadyIncludeCurrentStick =0;
                if(requiredPairSum-currVal==currVal){
                    numInvalidPairsWhichAlreadyIncludeCurrentStick=1;
                }
                
                int totalValidPairsWhichAlreadyIncludeCurrentStick = totalPairsWhichAlreadyIncludeCurrentStick-numInvalidPairsWhichAlreadyIncludeCurrentStick;
                
               int totalValidPairsWithRequiredPairSumAndDoesNotAlreadyIncludeCurrentStick = totalPairsWithRequiredPairSum -totalValidPairsWhichAlreadyIncludeCurrentStick;
               int totalPossibleTriplets = totalValidPairsWithRequiredPairSumAndDoesNotAlreadyIncludeCurrentStick;

                b[tripletSum] = totalPossibleTriplets;
            }
        }

        return b;
    }
    
    
    public int findNumSquares(int n,Integer[] a,int min,int max){
        int count=0;
        Integer[] singletArray = getCounter(a,max);
        Integer[] pairSumArray = findPairs(a,max,n);
        Integer[] tripletSumArray = findTriplets(a,n,min,max,pairSumArray,singletArray);
        
        
        return count;
    }
    
    public Integer[] getCounter(Integer[] a, int max){
       
        Integer[] b = new Integer[max+1];
        for(int i=0;i<=max;i++){
            b[a[i]]++;
        }
        
        return b;
        
    }

    public static int randInt(int min, int max) {

        Random rand= new Random();

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }
    
    
}
