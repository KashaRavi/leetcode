package amazon.maxproductPair;


// JAVA Code to Find a pair with maximum
// product in array of Integers
import java.util.*;

public class Solution {

    // Function to find maximum product pair
    // in arr[0..n-1]
    static void maxProduct(int arr[], int n)
    {
        if (n < 2)
        {
            System.out.println("No pairs exists");
            return;
        }

        if (n == 2)
        {
            System.out.println(arr[0] + " " + arr[1]);
            return;
        }

        // Iniitialize maximum and second maximum
        int posa = Integer.MIN_VALUE,
                posb = Integer.MIN_VALUE;

        // Iniitialize minimum and second minimum
        int nega = Integer.MIN_VALUE,
                negb = Integer.MIN_VALUE;

        // Traverse given array
        for (int i = 0; i < n; i++)
        {
            // Update maximum and second maximum
            // if needed
            if (arr[i] > posa)
            {
                posb = posa;
                posa = arr[i];
            }
            else if (arr[i] > posb)
                posb = arr[i];

            // Update minimum and second minimum
            // if needed
            if (arr[i] < 0 && Math.abs(arr[i]) >
                    Math.abs(nega))
            {
                negb = nega;
                nega = arr[i];
            }
            else if(arr[i] < 0 && Math.abs(arr[i])
                    > Math.abs(negb))
                negb = arr[i];
        }

        if (nega * negb > posa * posb)
            System.out.println("Max product pair is {"
                    + nega + ", " + negb + "}");
        else
            System.out.println("Max product pair is {"
                    + posa + ", " + posb + "}");
    }

    /* Driver program to test above function */
    public static void main(String[] args)
    {
        int arr[] = {1, -4, 3, 6, -7, 0};
        int n = arr.length;
        maxProduct(arr, n);

    }
}

// This code is contributed by Arnav Kr. Mandal.
