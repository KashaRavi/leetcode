package com.hackerrank.twoRobots;

import java.util.Scanner;

/**
 * @author rkasha
 */
public class solution {
    
    public static class Path{
        int a;
        int b;
        Path(int a, int b)
        {
            this.a = a;
            this.b = b;
        }        
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        for(int i=0;i<t;i++){
            int m=sc.nextInt();
            int n = sc.nextInt();
            Path[] queries = new Path[n];
            for(int j=0;j<n;j++){
                int a = sc.nextInt();
                int b = sc.nextInt();
                queries[j] = new Path(a,b);
            }
            

            int minDist = Integer.MAX_VALUE;
            
            for(int k = 1; k<n;k++){
                int r1Dist = Math.abs(queries[0].b-queries[0].a);
                int r1Node = queries[0].b;
                int prevb = queries[0].b;

                for(int l=1;l<k;l++){
                    r1Dist +=Math.abs(prevb-queries[l].a)+Math.abs(queries[l].b-queries[l].a);
                    prevb = queries[l].b;
                    r1Node=prevb;
                    
                }
                
                int totalDist = r1Dist + Math.abs(queries[k].b-queries[k].a);
                int r2Node = queries[k].b;
                
                for(int p = k+1;p<n;p++){
                    if(Math.abs(queries[p].a -r1Node) <= Math.abs(queries[p].a -r2Node)){
                        totalDist += Math.abs(queries[p].a -r1Node);
                        totalDist += Math.abs(queries[p].a -queries[p].b);
                        r1Node = queries[p].b;
                    } else {
                        totalDist += Math.abs(queries[p].a -r2Node);
                        totalDist += Math.abs(queries[p].a -queries[p].b);
                        r2Node = queries[p].b;
                    }
                }
                System.out.println("k= "+k+"dist="+totalDist);
                
                if(minDist > totalDist ){
                    minDist = totalDist;
                }
            }
            System.out.println(minDist);

        }
    }
}
