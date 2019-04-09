package thoughtworks;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkasha on 4/6/19.
 */
public class TestClass {

    public static void main(String[] args) {

        int k = 2;
        String s = "kababindia";
//        String s = "aabbccaa";
        System.out.println(getPartitionCount(s,k));
    }

    public static int getPartitionCount(String s, int k) {

        int freq[] = new int[26];
        int targetFreq[] = new int[26];

        //count char frequency
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        //characters that occur odd num times cannot contribute. So set them to zero
        //compute target frequency to consider the contribution of a character.
        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                freq[i] = 0;
            } else {
                targetFreq[i] = freq[i] / 2;
            }
        }

        int count = 0;
        int numPartitions = 0;

        for (int i = 0; i < s.length(); i++) {
            int ind = s.charAt(i) - 'a';
            freq[ind]--;
            if (freq[ind] >= 0) {
                if (freq[ind] == targetFreq[ind]) {
                    count++;
                } else if (freq[ind] < targetFreq[ind]) {
                    count--;
                    targetFreq[ind] = -1;
                }
            }

            if (count >= k) {
                numPartitions++;
            }
        }
        return numPartitions;
    }
}
