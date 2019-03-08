//https://www.geeksforgeeks.org/program-nth-catalan-number/
package algorithms.dynamicprogramming.catalannumber;

/**
 * Created by rkasha on 3/7/19.
 */
public class TestClass {

    public static void main(String[] args) {
        int n = 9;
        int[] arr = new int[n+1];
        arr[0]=1;
        System.out.println(catalan(arr, n));
    }

    public static int catalan(int[] arr, int n) {
        if(arr[n] != 0) return arr[n];

        for(int i=0;i<n;i++)  {
            arr[n] += catalan(arr, i)*catalan(arr, n-1-i);
        }
        return arr[n];
    }
}
