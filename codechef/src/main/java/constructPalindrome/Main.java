package com.codechef.constructPalindrome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rkasha on 11/15/2014.
 */
public class Main {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
        int T = Integer.parseInt(br.readLine());
        for(int i=0;i<T;i++){
            String s = br.readLine();
            boolean badCharacterAtj = false;
            boolean badCharacterAtk = false;
            int j=0,k=s.length()-1;
            while(j<=k){
                if(s.charAt(j) != s.charAt(k)){
                    badCharacterAtj = true;
                    badCharacterAtk = true;
                    break;
                }
                j++;k--;
            }
            if(j>k)
            {
                System.out.println("YES");
               //return;

            } else {
                int tempj = j;
                int tempk = k;
                //badCharacterAtk = false;
                j++;
                while(j<=k){
                    if(s.charAt(j) != s.charAt(k)){
                        badCharacterAtj = false;
                        break;
                    }
                    j++;k--;
                }
                if(badCharacterAtj) {

                System.out.println("YES");
                    continue;
                }

                j= tempj;
                k= tempk;

                k--;
                while(j<=k){
                    if(s.charAt(j) != s.charAt(k)){
                        badCharacterAtk = false;
                        break;
                    }
                    j++;k--;
                }
                if(badCharacterAtk){
                System.out.println("YES");
                    continue;
                }

                System.out.println("NO");

            }
        }
        } catch (IOException e){

        }

    }
}
