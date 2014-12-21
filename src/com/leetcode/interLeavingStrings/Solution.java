package com.leetcode.interLeavingStrings;

/**
 * Created by rkasha on 11/2/2014.
 */
public class Solution {
    public boolean isInterleave(String A, String B, String C) {
        int M = A.length(), N = B.length();

        // Let us create a 2D table to store solutions of
        // subproblems.  C[i][j] will be true if C[0..i+j-1]
        // is an interleaving of A[0..i-1] and B[0..j-1].
        boolean IL[][] = new boolean[M+1][N+1];

        //memset(IL, 0, sizeof(IL)); // Initialize all values as false.

        // C can be an interleaving of A and B only of sum
        // of lengths of A & B is equal to length of C.
        if ((M+N) != C.length())
            return false;

        // Process all characters of A and B
        for (int i=0; i<=M; ++i)
        {
            for (int j=0; j<=N; ++j)
            {
                // two empty strings have an empty string
                // as interleaving
                if (i==0 && j==0)
                    IL[i][j] = true;


                    // A is empty
                else if (i==0 && B.charAt(j-1)==C.charAt(j-1))
                    IL[i][j] = IL[i][j-1];

                else if (i==0 && B.charAt(j-1)!=C.charAt(j-1))
                    IL[i][j] = false;

                    // B is empty
                else if (j==0 && A.charAt(i-1)==C.charAt(i-1))
                    IL[i][j] = IL[i-1][j];

                else if (j==0 && A.charAt(i-1)!=C.charAt(i-1))
                    IL[i][j] = false;

                    // Current character of C matches with current character of A,
                    // but doesn't match with current character of B
                else if(A.charAt(i-1)==C.charAt(i+j-1) && B.charAt(j-1)!=C.charAt(i+j-1))
                    IL[i][j] = IL[i-1][j];

                    // Current character of C matches with current character of B,
                    // but doesn't match with current character of A
                else if (A.charAt(i-1)!=C.charAt(i+j-1) && B.charAt(j-1)==C.charAt(i+j-1))
                    IL[i][j] = IL[i][j-1];

                    // Current character of C matches with that of both A and B
                else if (A.charAt(i-1)==C.charAt(i+j-1) && B.charAt(j-1)==C.charAt(i+j-1))
                    IL[i][j]=(IL[i-1][j] || IL[i][j-1]) ;
            }
        }

        return IL[M][N];
    }
}
