package all_pairs_shortest_path;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by rkasha on 26-May-16.
 */
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] adjacencyMat = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            Arrays.fill(adjacencyMat[i],Integer.MAX_VALUE);
        }

        for(int i=0;i<m;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            adjacencyMat[x][y]=r;
        }

        for(int i=1;i<=n;i++){
            adjacencyMat[i][i]=0;
        }

        int[][] prevShortPaths = adjacencyMat;
        int[][] nextShortPaths = new int[n+1][n+1];
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    int pathSum = prevShortPaths[i][k]+prevShortPaths[k][j];
                    if(prevShortPaths[i][k] ==Integer.MAX_VALUE || prevShortPaths[k][j] == Integer.MAX_VALUE)
                        pathSum = Integer.MAX_VALUE;
                    nextShortPaths[i][j] = Math.min(prevShortPaths[i][j],pathSum);
                }
            }
            prevShortPaths =nextShortPaths;
        }

        int q = sc.nextInt();
        for(int i=0;i<q;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int pathAB = nextShortPaths[a][b];
            if(pathAB == Integer.MAX_VALUE)
                pathAB =-1;
            System.out.println(pathAB);
        }

//        int k=10;
//        String s = "";
//        for(int k=10;k<100;k++) {
//                s = s+" .";
//                System.out.print(s);
//                Thread.sleep(150);
//
////            for (int i = 0; i < k; i++) {
////            }
//            System.out.print("\r");
//        }
//        System.out.println();

    }
}
