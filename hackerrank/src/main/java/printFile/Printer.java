package printFile;

import java.util.Scanner;

/**
 * Created by rkasha on 22-May-16.
 */
public class Printer {
    public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    while(sc.hasNextLine()){
        System.out.println(sc.nextLine());
//        System.out.print(sc.nextLine());
    }

    }
}
