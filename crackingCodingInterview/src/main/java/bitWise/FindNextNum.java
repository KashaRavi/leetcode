package com.crackingCodingInterview.bitWise;

/**
 * @author rkasha
 */
public class FindNextNum {
    public static void main(String[] args){
        FindNextNum nextNum = new FindNextNum();
        int n=56;
        nextNum.printNextNum(n);
        System.out.println("Actual next=" + nextNum.getNext(n));
        System.out.println("Actual prev=" + nextNum.getPrev(n));
    }
    
    public void printNextNum(int num){
        int a=num;
        int startbit = num&1;
        
        
       int endPos=findSequenceEnd(num, 0, startbit);
        System.out.println(endPos);
        if(endPos==32){
            System.out.println("No next numbers");
            return;
        }
        
        int next = swap(num,endPos,endPos-1,startbit);
        if(startbit==0){
            System.out.println("Next smallest number:"+next);
            int oneSeqEnd = findSequenceEnd(num,endPos,1);
            System.out.println(oneSeqEnd);
            if(oneSeqEnd == 32){
            System.out.println("No Next biggest number:");
                return;
            } else{
            System.out.println("Next biggest number:"+swap(num,oneSeqEnd,oneSeqEnd-1,1));
                
            }
            
        } else{
            System.out.println("Next biggest number:"+next);
            int zeroSeqEnd = findSequenceEnd(num,endPos,0);
            if(zeroSeqEnd==32){
                
            System.out.println("No Next smallest number");
                return;
            } else {
            System.out.println("Next smallest number:"+swap(num,endPos,endPos-1,0));
            }
        }
        
        
        
    }
    
    int findSequenceEnd(int num,int pos,int startBit){
        num=num>>pos;
        while(pos<32 && (num&1)== startBit){
            pos++;
            num = num>>1;
        }
        return pos;
    }
    
    int swap(int num, int pos1,int pos2,int bitAtPos2){
        num = num & (~(3<<pos2));
        
        int bitAtPos1=bitAtPos2 << pos1;
        bitAtPos2 = bitAtPos2 ^ 1;
        bitAtPos2 = bitAtPos2 <<pos2;
        
        int mask  = bitAtPos1 | bitAtPos2;
        num=num | mask;
        return num;
    }
    
    
     int getNext(int n){
        int c=n;
         int c0=0;
         int c1=0;
         while(((c & 1) == 0) && (c != 0)) {
             c0++;
             c >>= 1;
         }
         while((c & 1) == 1){
             c1++;
              c >>= 1;
         }
         
         if(c0+c1 == 31 || c0+c1==0){
             return -1;
         }
         
         int p= c0+c1;
         n |= (1<<p);
          n &= ~((1 << p) -1);
          n |= (1 << (c1-1))-1;
         return n;
        
    }
    
    int getPrev(int n){
        int temp=n;
        int c0=0;
        int c1=0;
        while((temp & 1) == 1){
            c1++;
            temp >>=1;
        }
        
        if(temp==0){
            return  -1;
        }
        
        while(((temp &1)==0) && (temp !=0)){
            c0++;
            temp >>=1;
            
            
        }
        
        int p=c0+c1;
        n &=((~0) << (p+1));
        
        int mask = (1 << (c1+1))-1;
        n |= mask << (c0-1);
        return n;
        
    }
}
