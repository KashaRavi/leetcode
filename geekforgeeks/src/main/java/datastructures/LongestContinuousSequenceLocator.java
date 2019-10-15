//https://www.geeksforgeeks.org/find-index-0-replaced-1-get-longest-continuous-sequence-1s-binary-array/
package datastructures;

/**
 * Created by rkasha on 8/2/19.
 *
 * Note: this is not the right solution. For correct and efficient implementation look at g4g.
 */
public class LongestContinuousSequenceLocator {

    public static void main(String[] args) {

        //        int arr[] = {1, 2, 1, 3, 4, 2, 3};
        //                int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};
        //        int arr[] = {8,10,2};
        //        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        //        int arr[] = {-2, -3, 4, -1, -2, 1, 5, -3};
        //        int arr[] = {-2, -3, -4, -1, -2, -1, -5, -3};
//        int arr[] = { 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1 };
//        int arr[] = { 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1 };
        int arr[] = {1, 1, 1, 1, 0};

        int res = LongestContinuousSequenceIndex(arr);
    }

    public static int LongestContinuousSequenceIndex(int[] arr) {
        int curWingSize = 0;
        int prevWingSize = 0;
        int desiredInd = -1;
        int prevVal = -1;
        int maxlen = 0;
        int finalInd = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (prevVal == 1) {
                    curWingSize++;
                    int len = prevWingSize + curWingSize;
                    if (len > maxlen) {
                        maxlen = len;
                        finalInd = desiredInd;
                    }
                } else if (prevVal == 0) {
                    prevVal = 1;
                    curWingSize = 1;
                    desiredInd=i;
                    int len = prevWingSize + curWingSize;
                    if (len > maxlen) {
                        maxlen = len;
                        finalInd = desiredInd;

                    }
                } else {
                    curWingSize = 1;
                    prevVal = 1;
                }
            } else {
                if (prevVal == 1) {
                    prevWingSize = curWingSize;
                    prevVal = 0;
                    curWingSize = 0;
                    desiredInd = i;
                    int len = prevWingSize + curWingSize;
                    if (len > maxlen) {
                        maxlen = len;
                        finalInd = desiredInd;
                    }
                } else {
                    curWingSize = 0;
                    prevWingSize = 0;
                }
            }
        }
        System.out.println("ind=" + finalInd + " maxlen=" + (maxlen + 1));
        return finalInd;
    }

}
