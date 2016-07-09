package com.java;

/**
 * @author rkasha
 */
public class FinallyBlockTest {
    
    public static void main(String[] args){
        try{
            System.out.println("try");
//            System.exit(0);
//            Thread.currentThread().getThreadGroup().list();
            Thread.currentThread().destroy();

//            throw new Exception();
        }catch(Exception e){
            System.out.println("hai");
            return;
            } finally {
            System.out.println("finally");
//          throw new Exception();
            return;
        }

    }
}
