package counterGame;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by rkasha on 29-May-16.
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<t;i++){
//            String s = sc.nextLine();
            BigInteger v = sc.nextBigInteger();

            int totalOnes =0;
            int totalRightMostZeroes =0;
            boolean countZeroes=true;
            while(v.compareTo(new BigInteger("0")) > 0){
                BigInteger one = new BigInteger("1");
                BigInteger lastBit = v.and(new BigInteger("1"));
                if(lastBit.compareTo(one)==0){
                    totalOnes++;
                    countZeroes=false;
                } else if(countZeroes) {
                    totalRightMostZeroes++;
                }
                v = v.shiftRight(1);
            }
            int totalMoves = totalOnes+totalRightMostZeroes-1;
            if(totalMoves%2==0){
                System.out.println("Richard");
            } else {
                System.out.println("Louise");
            }
        }
    }
}
