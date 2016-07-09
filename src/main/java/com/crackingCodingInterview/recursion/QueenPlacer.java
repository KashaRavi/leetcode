package com.crackingCodingInterview.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rkasha
 */
public class QueenPlacer {
    static int count = 0;
   int GRID_SIZE=8;

    public static void main(String[] args)
    {
        QueenPlacer qplacer = new QueenPlacer();
        List<Integer[]> result = new ArrayList<Integer[]>();
        Integer[] columns = new Integer[8];
        qplacer.placeQueens(result,columns,0);
        System.out.println(count);
        for(Integer[] a:result) {
            for(Integer i:a){
                System.out.print("["+i+"]");
            }
            System.out.println();
        }
    }
    
    private void placeQueens(List<Integer[]> result,Integer[] columns,int row){
        if(row == GRID_SIZE){
            result.add(columns.clone());
            count++;
        }
        for(int col=0;col<GRID_SIZE;col++){
            if(isValidPositiion(columns,row,col)){
                columns[row]=col;
                placeQueens(result,columns,row+1);
            }
        }
    }
    
    private boolean isValidPositiion(Integer[] columns,int row,int col){
        for(int row1=0;row1<row;row1++){
            int col1= columns[row1];
            
            if(col1==col)
                return false;
        if(Math.abs(row-row1) == Math.abs(col-col1))
            return false;
        }
        return true;
    }
}
