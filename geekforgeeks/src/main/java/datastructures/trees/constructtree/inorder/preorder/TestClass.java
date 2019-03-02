package datastructures.trees.constructtree.inorder.preorder;

import tree.Node;

import static datastructures.trees.identicaltrees.TestClass.isSame;

/**
 * Created by rkasha on 3/2/19.
 */
public class TestClass {

    static int preIndex;
    static int postIndex;

    /**
     *  build the node, right node and left node.
     *  Traverse the postorder arr from end to start
     */
    private static Node<Integer> buildTreeFromInorderAndPostOrder(int[] inOrder, int[] postOrder,
            int start, int end) {

        if (start > end) {
            return null;
        }

        int val = postOrder[postIndex];
        Node<Integer> root = new Node<Integer>(val);
        int rootNodeIndex = search(inOrder, start, end, val);
        postIndex--;
        root.right = buildTreeFromInorderAndPostOrder(inOrder, postOrder, rootNodeIndex + 1, end);
        root.left = buildTreeFromInorderAndPostOrder(inOrder, postOrder, start, rootNodeIndex - 1);
        return root;
    }

    /**
     * build the node, left node and right node.
     * Traverse the preoder arr from start to end
     * @return
     */
    private static Node<Integer> buildTreeFromInorderAndPreOrder(int[] inOrder, int[] preOrder,
            int start, int end) {

        if (start > end) {
            return null;
        }

        int val = preOrder[preIndex++];

        Node<Integer> root = new Node<Integer>(val);
        int rootNodeIndex = search(inOrder, start, end, val);

        root.left = buildTreeFromInorderAndPreOrder(inOrder, preOrder, start, rootNodeIndex - 1);
        root.right = buildTreeFromInorderAndPreOrder(inOrder, preOrder, rootNodeIndex + 1, end);
        return root;
    }

    private static int search(int[] arr, int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] in = { 4, 2, 5, 1, 3 };
        int[] pre = { 1, 2, 4, 5, 3 };
        int[] po = { 4, 5, 2, 3, 1 };

        //        int[] in = { 4, 2, 5, 1, 8, 6, 9, 3, 0, 7, 1 };
        //        int[] pre = { 1, 2, 4, 5, 3, 6, 8, 9, 7, 0, 1 };
        //        int[] po = { 4, 5, 2, 8, 9, 6, 0, 1, 7, 3, 1 };

        preIndex = 0;
        Node<Integer> iopreRoot = buildTreeFromInorderAndPreOrder(in, pre, 0, in.length - 1);

        postIndex = po.length - 1;
        Node<Integer> iopoRoot = buildTreeFromInorderAndPostOrder(in, po, 0, in.length - 1);

        isSame(iopreRoot, iopoRoot);
    }
}
