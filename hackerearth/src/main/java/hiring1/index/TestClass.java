package hiring1.index;

import java.util.Scanner;

/**
 * Created by Ravi Kasha on 10-07-2016.
 */
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

//        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int init=0;
        for(int i=0;i<n;i++){
            init = init+sc.nextInt();
            a[i]=init;
        }

        int q = sc.nextInt();
        for(int i=0;i<q;i++){
            int val = sc.nextInt();
            System.out.println(findInd(a,n,val));
        }

    }

    public static int findInd(int[] a, int n, int num){
        int i=0,j=n-1;
        while(i<j){
            int k= (i+j)/2;
            if(a[k] < num){
                i = k+1;
            } else if(a[k]>num){
                j = k-1;
            }
            else{
                return k+1;
            }
        }

            if(num <=a[i]){
                return i+1;
            } else {
                return i+2;
            }

    }
}
