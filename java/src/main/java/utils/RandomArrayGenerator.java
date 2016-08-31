package utils;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Ravi Kasha on 13-07-2016.
 */
public class RandomArrayGenerator {
    public static void main(String[] args) throws IOException {
        int size = 8;
        int[] arr = new int[size];
        Random random = new Random();
        int min = 0;
        int max = 15;
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(max - min + 1) + min;
            System.out.print(arr[i] + " ");
        }
    }
}
