package hiring.expedia.question2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by rkasha on 1/22/19.
 */
public class TestClass {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        double[] values = new double[n];
        String line = br.readLine(); // to read multiple integers line
        String[] strs = line.trim().split("\\s+");
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(strs[i]);
        }

        int k = Integer.parseInt(br.readLine());

        System.out.println(getLongestGPSequenceLength(values, k, n));
    }

    static int getLongestGPSequenceLength(double[] v, int k, int n) {
        int[] lengths = new int[n];
        for(int i=0;i<n;i++) {
            lengths[i] = 1;
        }
        int lSeqLen = 1;
        for(int i=1;i<n;i++) {
            for(int j=0;j<i;j++) {
                if((v[j]*k <= v[i]) && (lengths[j]+1) > lengths[i]) {
                    lengths[i]=lengths[j]+1;
                }
            }
            if(lSeqLen < lengths[i]) {
                lSeqLen = lengths[i];
            }
        }
        return lSeqLen;
    }
}


