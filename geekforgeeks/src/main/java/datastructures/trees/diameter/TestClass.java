package datastructures.trees.diameter;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/**
 * Created by rkasha on 3/4/19.
 */

/*
                                                               1
                                                              /
                                                             /
                                                            /
                                                           /
                                                          /
                                                         /
                                                        /
                                                       /
                                                      /
                                                     /
                                                    /
                                                   /
                                                  /
                                                 /
                                                /
                                               /
                                              /
                                             /
                                            /
                                           /
                                          /
                                         /
                                        /
                                       /
                                      /
                                     /
                                    /
                                   /
                                  /
                                 /
                                /
                               /
                               2
                              / \
                             /   \
                            /     \
                           /       \
                          /         \
                         /           \
                        /             \
                       /               \
                      /                 \
                     /                   \
                    /                     \
                   /                       \
                  /                         \
                 /                           \
                /                             \
               /                               \
               4                               5
              /                                 \
             /                                   \
            /                                     \
           /                                       \
          /                                         \
         /                                           \
        /                                             \
       /                                               \
       3                                               6
                                                        \
                                                         \
                                                          \
                                                           \
                                                           8
                                                            \
                                                             \
                                                             9
                                                            /
                                                            10
 */
public class TestClass {

    static  int diameter =0;
    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(5);
        ITreeUtil.printTree(root);
        computeDiameter(root);
        System.out.println(diameter);
    }

    public static int computeDiameter(Node<Integer> root) {
        if(root == null) {
            return 0;
        }

        int rHeight = computeDiameter(root.right);
        int lHeight = computeDiameter(root.left);
        if(rHeight+lHeight + 1 > diameter)
            diameter = lHeight+rHeight + 1;
        return Math.max(lHeight,rHeight)+1;
    }
}
