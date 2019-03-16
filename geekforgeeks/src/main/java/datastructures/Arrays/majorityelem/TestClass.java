//https://www.geeksforgeeks.org/majority-element/

//It is an implementation of Boyer-Moore voting algorithm
//which depends on the fact if a majority element exists in a array then that element cannot be
// cancelled by another element. so at the end the uncancelled element will be the majority element
package datastructures.Arrays.majorityelem;

/**
 * Created by rkasha on 3/14/19.
 */
public class TestClass {
    public static void main(String[] args) {

        //        int arr[] = {1, 2, 1, 3, 4, 2, 3};
//                int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};
//        int arr[] = {8,10,2};
//        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4, 4};
        int arr[] = {3, 3, 4, 2, 4, 4, 2, 4};

        int k = 4;
        int output= findmajorityElem(arr);
        if(output !=-1) {
            System.out.println(output);
        }else {
            System.out.println("No majority elem");
        }
    }

    public static int findmajorityElem(int[] arr) {
        int n = arr.length;

        int count=1;
        int maj_elem= arr[0];
        for(int i=1;i<n;i++) {
            if(arr[i] == maj_elem){
                count++;
            } else {
                count--;
            }
            if(count==0) {
                maj_elem=arr[i];
                count=1;
            }
        }

        //potential majority element = maj_elem
        //verify if indeed maj_elem is the majority element

        count=0;
        for(int v: arr) {
            if(v == maj_elem){
                count++;
            }
            if(count > n/2){
                return maj_elem;
            }
        }
        return -1;

    }
}
