//https://www.geeksforgeeks.org/check-if-any-two-intervals-overlap-among-a-given-set-of-intervals/
package datastructures.Arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This solution is not a better one. For a better solution follow the above link
 *
 * The current solution is implemented following https://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 */
public class CheckForOverlappingIntervals {
//


    public static void main(String[] args) {

//        int[][] arr=  {{1, 3}, {5, 7}, {2, 4}, {6, 8}};
        int[][] arr=  {{1, 3}, {7, 9}, {4, 6}, {10, 13}};

        boolean hasOverlappingInterval = checkForOverlappingIntervals(arr);
        if(hasOverlappingInterval) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    static class Action {
        int time;
        String type;
    }

    public static boolean checkForOverlappingIntervals(int[][] arr) {
        int n= arr.length;
        Action[] actions= new Action[2*n];
        for(int i=0, j=0;i<arr.length;i++){
            Action a = new Action();
            a.time =arr[i][0];
            a.type="start";
            Action b = new Action();
            b.time =arr[i][1];
            b.type="end";
            actions[j]=a;
            actions[j+1]=b;
            j+=2;
        }

        Arrays.sort(actions, Comparator.comparingInt(a -> a.time));

        int count =0;
        for(int i=0;i<2*n;i++){
            if(actions[i].type.equals("start")){
                count++;
            } else {
                count--;
            }
            if(count>1){
                return true;
            }
        }
        return false;


    }
}
