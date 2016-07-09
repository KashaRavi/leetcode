package TestProgram;

import com.hackerrank.printFile.Printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by rkasha on 22-May-16.
 */
public class Solution {
    public static void main(String[] args) throws IOException, InterruptedException {
        call_all_pairs_shortest_paths();
    }

    static void call_dijkstra() throws IOException {
        //        FileInputStream fStream = new FileInputStream(new File("D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\dijkstra\\input00.txt"));
        FileInputStream fStream = new FileInputStream(new File(
            "D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\dijkstra\\input01.txt"));
        System.setIn(fStream);
        //        Printer printer = new Printer();
        //        printer.main(null);
        com.hackerrank.dijkstra.Solution sol = new com.hackerrank.dijkstra.Solution();
        sol.main(new String[] {});
        fStream.close();
    }

    static void call_all_pairs_shortest_paths() throws IOException, InterruptedException {
        String filename = "testOutput05.txt";
        FileInputStream ifStream = new FileInputStream(new File("D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\all_pairs_shortest_path\\input05.txt"));
        PrintStream pStream = new PrintStream(new File("D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\all_pairs_shortest_path\\"+ filename));

        PrintStream stdout = System.out;
        System.setIn(ifStream);
        System.setOut(pStream);

        com.hackerrank.all_pairs_shortest_path.Solution sol = new com.hackerrank.all_pairs_shortest_path.Solution();
        sol.main(new String[] {});

        ifStream.close();
        pStream.close();
        System.setIn(System.in);
        System.setOut(stdout);

        FileInputStream actualOfStream = new FileInputStream(new File("D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\all_pairs_shortest_path\\output05.txt"));
        FileInputStream resultOfStream = new FileInputStream(new File("D:\\rkasha\\My Projects\\LeetCodeOJ\\src\\main\\java\\com\\hackerrank\\all_pairs_shortest_path\\"+ filename));
        Scanner actualSC = new Scanner(actualOfStream);
        Scanner resultSC = new Scanner(resultOfStream);

        int line = 0;
        while (actualSC.hasNextLine() && resultSC.hasNextLine()) {
            String result = resultSC.nextLine();
            String actual = actualSC.nextLine();
            if(!result.equals(actual))
            System.out.println(++line + ":" + resultSC.nextLine() + "-" + actualSC.nextLine());
        }
    }


}
