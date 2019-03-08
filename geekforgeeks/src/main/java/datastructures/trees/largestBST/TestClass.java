//https://www.geeksforgeeks.org/largest-bst-binary-tree-set-2/
package datastructures.trees.largestBST;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;

/*
    5
   / \
  2  4
 / \
1  3

root Node:2 Size:3

*/

/**
 * Created by rkasha on 3/9/19.
 */
public class TestClass {
    public static void main(String[] args) throws IOException {
        Node<Integer> root = ITreeUtil.getTree(6);
        ITreeUtil.printTree(root);
        Info info = findLargestBST(root);
        int val = info.bstRoot != null ? info.bstRoot.data : -1;
        System.out.println("root Node:" + val + " Size:" + info.ans);
    }

    //
    static class Info {
        int min;
        int max;
        int ans;
        int size;
        boolean isBST;
        Node<Integer> bstRoot;

        Info(int min, int max, int ans, int size, boolean isBST, Node<Integer> node) {
            this.min = min;
            this.max = max;
            this.ans = ans;
            this.size = size;
            this.isBST = isBST;

            this.bstRoot = node;
        }
    }

    public static Info findLargestBST(Node<Integer> root) {

        if (root == null) {
            return new Info(Integer.MAX_VALUE, Integer.MIN_VALUE, 0, 0, true, null);
        }
        if (root.left == null && root.right == null) {
            return new Info(root.data, root.data, 1, 1, true, root);
        }

        //since we need info from both the branches => we have to call both the branches before making
        //any descisions.
        Info lInfo = findLargestBST(root.left);
        Info rInfo = findLargestBST(root.right);

        //Make decisions at the current node
        if (lInfo.isBST && rInfo.isBST && lInfo.max <= root.data && rInfo.min > root.data) {

            return new Info(
                    Math.min(root.data, Math.min(lInfo.min, rInfo.min)),
                    Math.max(root.data, Math.max(lInfo.max, rInfo.max)),
                    lInfo.size + rInfo.size + 1,
                    lInfo.size + rInfo.size + 1,
                    true,
                    root);
        } else {
            int ans = lInfo.ans > rInfo.ans ? lInfo.ans : rInfo.ans;
            Node<Integer> node =
                    lInfo.ans > rInfo.ans ? lInfo.bstRoot : rInfo.bstRoot;

            return new Info(
                    Math.min(root.data, Math.min(lInfo.min, rInfo.min)),
                    Math.max(root.data, Math.max(lInfo.max, rInfo.max)),
                    ans,
                    lInfo.size + rInfo.size + 1,
                    false,
                    node);
        }
    }
}
