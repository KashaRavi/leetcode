package lucky;

/**
 * @author rkasha
 */
/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;
*/
import java.util.*;

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

        Scanner sc = new Scanner(System.in);
        int t= sc.nextInt();sc.nextLine();
        for(int i=0;i<t;i++){

            int size=0;
            String s = sc.nextLine();
            StringBuffer sb = new StringBuffer(s);

            while(sb.charAt(0) != 'R'){
                sb = sb.deleteCharAt(0);
            }

            while(sb.charAt(sb.length()-1) != 'K'){
                sb = sb.deleteCharAt(sb.length()-1);
            }

            if(sb.length()==0){
                System.out.println(0);
                continue;
            }

            int rC=0,kC=0;
            for(int j=0;j<sb.length();j++){
                if(sb.charAt(j)=='R') rC++;
                else kC++;
            }

            int xrc = 0;
            int xkc=0;
            if(rC > kC){
                xrc = rC-kC;
            } else {
                xkc= kC-rC;
            }

            if(xrc > 0){
                rC=rC-xrc;
                while(xrc >0){
                    int rInd = sb.lastIndexOf("R");
                    sb= sb.deleteCharAt(rInd);
                    xrc--;
                }

            } else if(xkc > 0){
                kC=kC-xkc;
                while(xkc >0){
                    int kInd = sb.indexOf("K");
                    sb= sb.deleteCharAt(kInd);
                    xkc--;
                }
            }

            for(int c = rC;c>=0;c-- ){

                if(!checkValid(sb,c)){
                    sb=sb.deleteCharAt(sb.indexOf("K"));
                    sb=sb.deleteCharAt(sb.lastIndexOf("R"));
                } else {
                    System.out.println(2*c);
                    break;
                }

            }

        }
    }

    private static boolean checkValid(StringBuffer sb,int count){
        for(int i=0;i<count;i++){
            if(sb.charAt(i) !='R') return false;
        }
        return true;
    }
}
