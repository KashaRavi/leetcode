package datastructures.hashing.longestcontiguoussubsequence;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rkasha on 3/13/19.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
//       Integer[] arr = {1, 9, 3, 10, 4, 20, 2};
       Integer[] arr = {36, 41, 56, 35, 44, 33, 34, 92, 43, 32, 42};
        System.out.println(longestContiguousSubSequence(arr));
    }

    public static int longestContiguousSubSequence(Integer[] arr) {
        Set<Integer> set = new HashSet<>(Arrays.asList(arr));
        int ans = 0;

        for(int i=0;i<arr.length;i++) {

            //if the prev number is already present in the set, then we have already counted this
            // element as part of some subsequence.
            if(!set.contains(arr[i]-1)){
                // This means arr[i] is not already part of any contiguous sequence discovered so far.
                //So now start looking for a contiguous sequence starting from arr[i]
                int elem = arr[i];
                //check if some of the contiguous elements are there in the array.
                while(set.contains(elem)) {
                    elem++;
                }

                int subseqLen =  elem-arr[i];
                ans = Math.max(ans, subseqLen);
            }
        }
        return ans;
    }


}
