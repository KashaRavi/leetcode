package datastructures.trees.lowestcommonancestor;

import datastructures.linkedlist.LLUtil;
import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/6/19.
 */
public class TestClass {

    static  Node<Integer> lca = null;

    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(3);
        ITreeUtil.printTree(root);
        ITreeUtil.printInorder(root);
        if(hasNodes(root, 2,3)==3){
            System.out.println(lca.data);
        }
    }

    //0 represents no node is present
    //1 represents a is present
    //2 represents b is present
    //3 represents both a and b are present
    //
    public static int hasNodes(Node<Integer> root, int a, int b) {

        if(root==null) {
            return 0;
        }

        int lVal = hasNodes(root.left, a, b);

        if(lVal ==3) {
            return  lVal;
        }

        int rVal = hasNodes(root.right, a, b);

        if(rVal == 3) {
            return rVal;
        }

        int curVal = 0;
        if(root.data == a ) {
            curVal = 1;
        } else if(curVal == b) {
            curVal = 2;
        }

        if((lVal ^ rVal) ==3 || (curVal ^ lVal) == 3 || (curVal ^ rVal) ==3 ) {
            lca = root;
            return 3;
        }

        return curVal^lVal^rVal;

    }

}

