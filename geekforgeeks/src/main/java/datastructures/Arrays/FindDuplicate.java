//https://www.geeksforgeeks.org/duplicates-array-using-o1-extra-space-set-2/
//https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/
package datastructures.Arrays;

/**
 * Created by rkasha on 3/16/19.
 */

class FindDuplicate
{
    // Function to print duplicates
    // This method does not find repitions of 0.
    //Also if a number is repeated more than two times it will print that number multiple times.
    void printRepeatingV1(int arr[], int size)
    {
        int i;
        System.out.println("The repeating elements are : ");

        for (i = 0; i < size; i++)
        {
            if (arr[Math.abs(arr[i])] >= 0)
                arr[Math.abs(arr[i])] = -arr[Math.abs(arr[i])];
            else
                System.out.print(Math.abs(arr[i]) + " ");
        }
    }

    /**
     *  This method works only under the given constraints.
     *  This method records tha fact that a value 'v' is repeting by incrementing the value at index=v by the size;
     *  After incrementing a value the original value at a index 'v' can be restored/obtained as arr[v]%n;
     *  If the value at an index 'v' has got incremented more than once then that means v has repeated more than once.
     *  If a value 'v' appeared only once then then there would have been only one increment to the value at index=v;
     */

    //This method handles zeroes and multiple repitions of same element
    //integer overflow problem can occur if a value is repeated too many times.
    void printRepeatingV2(int arr[], int size)
    {
        for (int i = 0; i < size; i++) {
            int index = arr[i]%size;
            arr[index]+=size;
        }

        for (int i = 0; i < size; i++) {
            if(arr[i]/size > 1){
                System.out.print(i+" ");
            }
        }
    }

    public static void main(String[] args)
    {
        FindDuplicate duplicate = new FindDuplicate();
        int arr[] = {1, 2, 3, 1, 3, 6, 6};
        int arr_size = arr.length;

//        duplicate.printRepeatingV1(arr, arr_size);
        duplicate.printRepeatingV2(arr, arr_size);
    }
}
