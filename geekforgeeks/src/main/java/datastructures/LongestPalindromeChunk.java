package datastructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LongestPalindromeChunk {
    public static void main(String[] args) {
        String str = "ghiabcdefhelloadamhelloabcdefghi";
        str = "merchant";
        str = "antaprezatepzapreanta";
        str = "geeksforgeeks";
        str = "ghiabcdefhelloadamhelloabcdefghik";
        str = "antaprezatepzapreanta";
        str = "VOLVO";
        str = "V";
        int count = countLongestPalindromeChunk( str);
        System.out.println(count);
    }

    public static int countLongestPalindromeChunk(String str) {
        Stack<Character> stk1 = new Stack<>();
        Stack<Character> stk2 = new Stack<>();
        Queue<Character> q1 = new LinkedList<>();
        Queue<Character> q2 = new LinkedList<>();

        int l = 0;
        int r = str.length()-1;

        int count =0;
        while(l<r){
            q1.add(str.charAt(l));
            l++;
            stk1.push(str.charAt(r));
            r--;

            while (!q1.isEmpty() && !stk1.isEmpty() && q1.peek().equals(stk1.peek())){
                q2.add(q1.remove());
                stk2.add(stk1.pop());
            }

            if(q1.isEmpty() && stk1.isEmpty()) {
                count += 2;
                q2.clear();
                stk2.clear();
            } else {
                Queue<Character> temp = q1;
                while(!q1.isEmpty()){
                    q2.add(q1.remove());
                }

                q1 = q2;
                q2 = temp;

                while (!stk2.isEmpty()) {
                    stk1.add(stk2.pop());
                }
            }
        }
        if (l == r) {
            count++;
        } else if (l > r && !q1.isEmpty()) {
            count++;
        }

        return count;
    }

}
