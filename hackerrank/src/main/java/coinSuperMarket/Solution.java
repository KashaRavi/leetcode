package coinSuperMarket;

/**
 * @author rkasha
 */
import java.io.*;
import java.util.*;
import java.math.BigInteger;


import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long [] a = new long[n];
        for(int i=0;i<n;i++){
            a[i] = sc.nextLong();
        }
        long[] x = new long[1<<n];
        x[0]=1;
        for(int i=1;i<(1<<n);i++){
            int currElementIndex = Integer.numberOfTrailingZeros(i);
            int previousSubSetIndex = i^(1<<currElementIndex);
            x[i] = x[previousSubSetIndex]/gcd(x[previousSubSetIndex],a[currElementIndex])*a[currElementIndex];

        }
        int d = sc.nextInt();
        for(int i=0;i<d;i++){
            long l= sc.nextLong();
            long r = sc.nextLong();
            long count=0;
            for(int j=1;j < (1 << n);j++){
                if(Integer.bitCount(j)%2==0){
                    count-=r/x[j]-(l-1)/x[j];

                } else {
                    count+=r/x[j]-(l-1)/x[j];
                }
            }
            System.out.println(count);
        }
    }

    public static long gcd(long a, long b) {
        while (b > 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        return a;
    }
}
