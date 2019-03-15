//This question is asked in pramp interview
//https://www.geeksforgeeks.org/a-product-array-puzzle/
package datastructures.Arrays.productofarrayelems;

import datastructures.Arrays.ArrayUtils;

/**
 * Created by rkasha on 3/14/19.
 */
public class TestClass {
    public static void main(String[] args) {

        //        int arr[] = {1, 2, 1, 3, 4, 2, 3};
//        int arr[] = {1, 2, 4, 5,6, 1, 2 , 3, 2, 2, 3};
        int arr[] = {8,10,2};

        int k = 4;
        int[] output= arrayOfArrayProducts(arr);
        ArrayUtils.printArray(output);
    }

    public static int[] arrayOfArrayProducts(int[] arr){
        int n= arr.length;

        if(n==0 || n==1){
            return new int[n];
        }

        int[] productArr = new int[n];
        int product =1;
        for(int i=0;i<n;i++) {
            productArr[i] = product;
            product*=arr[i];
        }

        product=1;
        for(int i=n-1;i>=0;i--) {
            productArr[i]*=product;
            product*=arr[i];
        }
        return productArr;
    }
}
