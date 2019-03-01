package tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rkasha on 3/2/19.
 */
public class CharacterBinaryTreeUtil {
    static int preIndex = 0;
    static Map<Integer, Node<Integer>> charTreeMap = new HashMap<Integer, Node<Integer>>();

    /* Recursive function to construct binary of size len from
    Inorder traversal in[] and Preorder traversal pre[].
    Initial values of inStrt and inEnd should be 0 and len -1.
    The function doesn't do any error checking for cases where
    inorder and preorder do not form a tree */
    static Node buildTree(Character[] in, Character[] pre, int inStrt, int inEnd) {
        if (inStrt > inEnd)
            return null;

		/* Pick current node from Preorder traversal using preIndex
        and increment preIndex */
        Node tNode = new Node(pre[preIndex++]);

		/* If this node has no children then return */
        if (inStrt == inEnd)
            return tNode;

		/* Else find the index of this node in Inorder traversal */
        int inIndex = search(in, inStrt, inEnd, (Character) tNode.data);

		/* Using index in Inorder traversal, construct left and
		right subtress */
        tNode.left = buildTree(in, pre, inStrt, inIndex - 1);
        tNode.right = buildTree(in, pre, inIndex + 1, inEnd);

        return tNode;
    }


    /* Function to find index of value in arr[start...end]
    The function assumes that value is present in in[] */
    static int search(Character[] arr, int strt, int end, Character value) {
        int i;
        for (i = strt; i <= end; i++) {
            if (arr[i].equals(value))
                return i;
        }
        return i;
    }


}
