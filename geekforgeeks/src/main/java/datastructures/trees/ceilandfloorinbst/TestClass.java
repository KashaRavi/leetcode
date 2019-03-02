package datastructures.trees.ceilandfloorinbst;

import tree.Node;
import tree.ITreeUtil;

import java.io.IOException;

/**
 * Created by rkasha on 3/1/19.
 */
public class TestClass {

    public static int ceil(Node<Integer> root, int val) {
        return ceil(root, null, null, val);
    }

    public static int floor(Node<Integer> root, int val) {
        return floor(root, null, null, val);
    }

    public static int ceil(Node<Integer> root, Node<Integer> predecessor,
            Node<Integer> successor, int val) {
        if (root != null) {
            if (root.data == val)
                return val;
            else if (val < root.data) {
                if (root.left != null) {
                    return ceil(root.left, predecessor, root, val);
                } else {
                    return root.data;
                }
            } else {
                if (root.right != null) {
                    return ceil(root.right, root, successor, val);
                } else {
                    if (successor != null) {
                        return successor.data;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public static int floor(Node<Integer> root, Node<Integer> predecessor,
            Node<Integer> successor, int val) {
        if (root != null) {
            if (root.data == val)
                return val;
            else if (val < root.data) {
                if (root.left != null) {
                    return floor(root.left, predecessor, root, val);
                } else {
                    return predecessor != null ? predecessor.data : Integer.MIN_VALUE;
                }
            } else {
                return root.right != null ? floor(root.right, root, successor, val) : root.data;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) throws IOException {
        ITreeUtil.printTree(2);
        Node<Integer> root = ITreeUtil.getTree(2);

        System.out.println("ceil(10):" + ceil(root, 10));
        System.out.println("floor(10):" + floor(root, 10));

        System.out.println("ceil(3):" + ceil(root, 3));
        System.out.println("floor(3):" + floor(root, 3));

        System.out.println("ceil(99):" + ceil(root, 99));
        System.out.println("floor(99):" + floor(root, 99));

        System.out.println("ceil(1):" + ceil(root, 1));
        System.out.println("floor(1):" + floor(root, 1));

        System.out.println("ceil(1000):" + ceil(root, 1000));
        System.out.println("floor(1000):" + floor(root, 1000));
    }
}
