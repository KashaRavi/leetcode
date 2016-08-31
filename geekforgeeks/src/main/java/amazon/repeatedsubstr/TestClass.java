package amazon.repeatedsubstr;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Ravi Kasha on 16-07-2016.
 */
public class TestClass {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<t;i++){
            String line = sc.nextLine();
            Map<Character,Integer> map = new HashMap<Character, Integer>();
            int[] a = new int[26];
            Arrays.fill(a,0);
            for(char c : line.toCharArray()){
                a[c-'a']++;
            }
            int min = Integer.MAX_VALUE;
            for(int l:a){
                if(l !=0 && l < min){
                    min=l;
                }
            }

            int val =0;
            for(int j=0;j<a.length;j++){
                val = gcd(val,a[j]);
            }

            int size = 0;
            for(int k:a){
                size += (k/val);
            }

            if(size == line.length() || line.length()%size !=0){
                System.out.println("False");
                return;
            }

            String reqStr = line.substring(0,size);
            while(line.endsWith(reqStr)){
                line  = line.substring(0,line.length()-size);
            }

            if(line.equals("")){
                System.out.println("True");
            } else {
                System.out.println("False");
            }
        }
    }

    public static int gcd(int n1,int n2) {
        int r;

        int min = Math.min(n1,n2);
        n1= Math.max(n1,n2);
        n2 =min;
        while (n2 != 0) {
            r = n1 % n2;
            n1 = n2;
            n2 = r;
        }
        return n1;
    }


}
