package validstrings;

import java.util.Scanner;

/**
 * Created by rkasha on 03-Jul-16.
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

        //System.out.println("Hello World!");
        final int  m = 1000000007;
        Scanner sc = new Scanner(System.in);
        int q= sc.nextInt();
        for(int i=0;i<q;i++){
            int n= sc.nextInt();
            int l = sc.nextInt();
            if(l > n){
                int val = power(n-1,n,m);
                int temp = power(n,l-n,m);
                long temp1 = (long)val*(long)temp;
                System.out.println(temp1%m);

            } else if(l==n){
                System.out.println(power(n-1,n,m));
            } else {
                System.out.println(power(n-1,l,m));
            }

        }
    }

        public static int power(int x, int y, int p)
        {
            int res = 1;      // Initialize result

            x = x % p;  // Update x if it is more than or
            // equal to p

            while (y > 0)
            {
                // If y is odd, multiply x with result
                if ((y & 1) ==1)
                    res = (res*x) % p;

                // y must be even now
                y = y>>1; // y = y/2
                x = (x*x) % p;
            }
            return res;
        }
}
