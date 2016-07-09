package com.codechef.longestDistinctSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main (String[] args)
    {
        try {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean set[] = new boolean[26];
        int numberOfStrings  = Integer.parseInt(br.readLine());
            int longestSubsequenceSize = 0;
            for(int i=0;i< numberOfStrings;i++){
                String line = br.readLine();
                for(int j=0;j<line.length();j++){
                    int index = line.charAt(j)-'a';
                    if(!set[index]){
                        set[index] = true;
                        longestSubsequenceSize++;
                    }
                }
                System.out.println(longestSubsequenceSize);
                longestSubsequenceSize=0;
                for(int k=0;k<26;k++)
                    set[k] = false;
            }

        } catch (IOException e)
        {
            System.out.println("Unable to catch...");
        }
    }
}
