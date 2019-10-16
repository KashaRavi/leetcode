package datastructures.Arrays;

public class PrintAllAnagrams {

    static int count = 0;

    public static void main(String[] args) {
        String str = "abcdefghij";
        char[] arr = str.toCharArray();
        printAllPermutations(arr, 0, arr.length - 1);
        System.out.println(count);
    }

    public static void printAllPermutations(char[] word, int l, int r) {
        if (l == r) {
            System.out.println(word);
            count++;
            return;
        }
        for (int i = l; i <= r; i++) {
            ArrayUtils.swap(word, l, i);
            printAllPermutations(word, l + 1, r);
            ArrayUtils.swap(word, l, i);
        }
    }
}
