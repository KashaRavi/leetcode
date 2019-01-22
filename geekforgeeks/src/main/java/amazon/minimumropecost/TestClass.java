//https://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/

package amazon.minimumropecost;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by Ravi Kasha on 16-07-2016.
 */
public class TestClass {
    public static void main (String[] args) {
        //code
        Scanner sc  = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i=0;i<t;i++){
            int n= sc.nextInt();
            int[] a = new int[n];
            Queue<Integer> pq = new PriorityQueue<Integer>();
            for(int j=0;j<n;j++){
                pq.add(sc.nextInt());
            }
            int cost =0;
            while(pq.size() >1){
                int r1= pq.poll();
                int r2=pq.poll();
                int r3 = r1+r2;
                cost+=r3;
                pq.add(r3);
            }
            System.out.println(cost);
        }
    }
}
