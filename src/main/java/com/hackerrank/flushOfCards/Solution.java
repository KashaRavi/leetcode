package com.hackerrank.flushOfCards;

/**
 * @author rkasha
 */
import java.io.*;
import java.util.*;

public class Solution {

    static class Card {
        int val;
        char suit;
        Card(int val,char suit){
            this.val=val;
            this.suit=suit;
        }
    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Map<Character,Integer> map = new HashMap<Character,Integer>();
        map.put('T',10);
        map.put('J',11);
        map.put('Q',12);
        map.put('K',13);
        map.put('A',14);


        Scanner sc = new Scanner(System.in);
        Card[] cards = new Card[5];
        for(int i=0;i<5;i++){
            String rep = sc.nextLine();

            char c =rep.charAt(0);
            if(map.get(c) !=null){
                cards[i] = new Card(map.get(new Character(c)),rep.charAt(1));
            } else {
                cards[i] = new Card(rep.charAt(0)-'0',rep.charAt(1));
            }
        }

        Comparator<Card> comp = new Comparator<Card>(){
            @Override
            public int compare(Card a, Card b) {
                return a.val - b.val;
            }
        };

        Arrays.sort(cards,comp);
        int cardVal = cards[0].val;
        boolean isFlush=true;
        for(int i=1;i<5;i++){
            if(cards[i].val-cardVal == 1){
                continue;
            } else {
                isFlush=false;
                break;
            }
        }

        if(!isFlush){

            if(cards[4].val==14){
                isFlush=true;
                cardVal = cards[0].val;
                for(int i=1;i<4;i++){
                    if(cards[i].val-cardVal == 1){
                        continue;
                    } else {
                        isFlush=false;
                        break;
                    }
                }
            }
        }

        if(isFlush){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }
}