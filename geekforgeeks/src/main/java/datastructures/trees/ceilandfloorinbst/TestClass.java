package datastructures.trees.ceilandfloorinbst;

import tree.Node;
import tree.TreeUtil;

import java.io.IOException;

/**
 * Created by rkasha on 3/1/19.
 */
public class TestClass {

    /*
              9
            /   \
          5      12
         /  \    / \
        3    6  11  14
                   /
                  13
*/

    public static void main(String[] args) throws IOException {
        Node<Integer> root = TreeUtil.constructIntTree();
        System.out.println("ceil(10):"+ceil(root, null, null, 10));
        System.out.println("ceil(3):"+ceil(root, null, null, 3));
        System.out.println("ceil(99):"+ceil(root, null, null, 99));
        System.out.println("ceil(1):"+ceil(root, null, null, 1));
        System.out.println("ceil(1000):"+ceil(root, null, null, 1000));
    }

    public static int ceil(Node<Integer> root, Node<Integer> leftAncestor, Node<Integer> rightAncestor, int val) {
        if(root != null) {
            if(root.data == val)
                return val;
            else if(val < root.data) {
                if(root.left != null) {
                    return ceil(root.left, leftAncestor, root, val);
                } else {
                    return root.data;
                }
            } else {
                if(root.right != null) {
                    return ceil(root.right, root, rightAncestor, val);
                } else {
                    if(rightAncestor!=null) {
                        return rightAncestor.data;
                    }else {
                        return Integer.MAX_VALUE;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
