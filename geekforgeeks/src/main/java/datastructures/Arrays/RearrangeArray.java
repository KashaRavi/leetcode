package datastructures.Arrays;

/**
 * Created by rkasha on 3/19/19.
 */
class RearrangeArray {

    public static void reOrderElementsWithOrder(int[] arr){
        int n= arr.length;
        int firstOutOfOrderIndex = -1; //-1 indicates no such index is found
        for(int i=0;i<n;i++){
            if(firstOutOfOrderIndex>=0){
                if(arr[firstOutOfOrderIndex]>=0 && arr[i]<0 || arr[firstOutOfOrderIndex] <0 && arr[i]>=0){
                    int temp = arr[i];
                    //right shift all the elements from firstOutOfOrderIndex to i by 1
                    for(int j=i;j>firstOutOfOrderIndex;j--){
                        arr[j]=arr[j-1];
                    }
                    //current element should replace the  element at firstOutOfOrderIndex
                    arr[firstOutOfOrderIndex]=temp;

                    //update firstOutOfOrderIndex to the next out-of-order element
                    if((i-firstOutOfOrderIndex)> 2){
                        //since all the elements from firstOutOfOrderIndex+1 to i are of the same sign as the original firstOutOfOrderIndex,
                        //we can safely update firstOutOfOrderIndex without missing anything
                        firstOutOfOrderIndex+=2;
                    }else {
                        //Since value at firstOutOfOrderIndex is fixed we need to retest to check if the current element is not in its place
                        firstOutOfOrderIndex=-1;
                    }
                }
            }

            //No previous element was out-of-order. Check if current element is out of order.
            if(firstOutOfOrderIndex == -1){
                if((arr[i] < 0) && ((i % 2)==1) || (arr[i] >= 0) && ((i % 2)==0)){
                    firstOutOfOrderIndex = i;
                }
            }
        }

    }


    public static void main(String[] args) {
        int arr[] = { 2, 3, -1, -4 };
        int n = arr.length;
        reOrderElementsWithOrder(arr);
        ArrayUtils.printArray(arr);
    }
}