package weekofcode21.luckbalance;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<Integer> impContests = new ArrayList<>();
        int totalSum = 0;

        for (int i = 0; i < n; i++) {
            int l = sc.nextInt();
            int t = sc.nextInt();
            totalSum += l;
            if (t == 1) {
                impContests.add(l);
            }
        }
        int numImpContests = impContests.size();
        if (k >= numImpContests) {
            System.out.println(totalSum);
            return;
        } else {
            int minAllowedWins = numImpContests - k;
            Collections.sort(impContests, (o, e) -> {
                return o - e;
            });
            int totalWinSum = 0;
            for (Integer i : impContests) {
                totalWinSum += i;
                minAllowedWins--;
                if (minAllowedWins <= 0)
                    break;
            }
            System.out.println(totalSum - 2 * (totalWinSum));
        }
    }
}