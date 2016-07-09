package trees;

import java.util.Arrays;
import java.util.List;

/**
 * @author rkasha
 */
public class BSTChecker {
    
    public static void main(String args[]){
        BSTChecker bstChecker  = new BSTChecker();
        BinaryTreeBuilder binaryTreeBuilder = new BinaryTreeBuilder();
//        Integer[] a = new Integer[]{1,4,6,23,54,11,6,3,7,8,6,9,3,0};
//        Integer[] a = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13};
        Integer[] a = new Integer[]{13,13,13,13};

        List<Integer> list = Arrays.asList(a);
        Node root = binaryTreeBuilder.buildBinaryTree(list,0,list.size()-1);
        if(bstChecker.checkIfBST(root)){
            System.out.println("tree is bst");
                  return;
        }
        System.out.println("tree is not bst");
    }
    
    public boolean checkIfBST(Node root){
        
        if(root == null){
            return true;
        }
        
        if(root.left != null && root.val < root.left.val){
            return false;
        }
        
        if(root.right != null && root.val >= root.right.val){
            return false;
        }
        
        if(!checkIfBST(root.left)  || !checkIfBST(root.right)){
            return false;
        }
        return true;
        
    }
    
    
    
}
