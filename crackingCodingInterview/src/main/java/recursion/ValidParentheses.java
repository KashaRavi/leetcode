package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rkasha
 */
public class ValidParentheses {
    
    public static void main(String[] args){
        int count = 0;
        char parArray[] = new char[count*2];
        List<String> list = new ArrayList<String>();
        ValidParentheses obj = new ValidParentheses();
        obj.addParentheses(list,parArray,count,count,0);
        for(String str:list){
            System.out.println(str);
        }
            System.out.println(list.size());
    }
    
    private void addParentheses(List<String> list,char[] parens,int numOpenBraces,int numCloseBraces,int index)
    {
        if(numOpenBraces < 0 || numCloseBraces < numOpenBraces) {
            System.out.println("Invalid state----------------");
            return;
        }
        
        if(numOpenBraces ==0 && numCloseBraces==0){
            String s = String.copyValueOf(parens);
            list.add(s);
        }
        
        if(numOpenBraces>0){
            char openBrace = '(';
            parens[index] = openBrace;
            addParentheses(list,parens,numOpenBraces-1,numCloseBraces,index+1);
        }
        
        if(numCloseBraces > numOpenBraces){
            parens[index]=')';
            addParentheses(list,parens,numOpenBraces,numCloseBraces-1,index+1);
        }
        
    }
}
