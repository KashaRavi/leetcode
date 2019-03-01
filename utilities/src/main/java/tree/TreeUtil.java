package tree;

// Java program to construct a tree using inorder and preorder traversal

/* A binary tree node has data, pointer to left child
and a pointer to right child */

// Java program to construct a tree using inorder and preorder traversal

import libraries.ReadJavaProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TreeUtil {
    Node root;
    static int preIndex = 0;

    /* Recursive function to construct binary of size len from
    Inorder traversal in[] and Preorder traversal pre[].
    Initial values of inStrt and inEnd should be 0 and len -1.
    The function doesn't do any error checking for cases where
    inorder and preorder do not form a tree */
    Node buildTree(Character[] in, Character[] pre, int inStrt, int inEnd) {
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

	/* UTILITY FUNCTIONS */

    /* Function to find index of value in arr[start...end]
    The function assumes that value is present in in[] */
    int search(Character[] arr, int strt, int end, Character value) {
        int i;
        for (i = strt; i <= end; i++) {
            if (arr[i].equals(value))
                return i;
        }
        return i;
    }

    /* This funtcion is here just to test buildTree() */
    void printInorder(Node node) {
        if (node == null)
            return;

		/* first recur on left child */
        printInorder(node.left);

		/* then print the data of node */
        System.out.print(node.data + " ");

		/* now recur on right child */
        printInorder(node.right);
    }

    // driver program to test above functions
    public static void main(String args[]) throws IOException {

        TreeUtil tree = new TreeUtil();
        Node root = constructTree(tree);
        // building the tree by printing inorder traversal
        System.out.println("Inorder traversal of constructed tree is : ");
        tree.printInorder(root);
        System.out.println();
        BTreePrinter.printNode(root);
    }

    public static Node constructTree(TreeUtil tree) throws IOException {
        InputStream inpStream;
        inpStream = ReadJavaProperties.class.getClassLoader()
                .getResourceAsStream("trees.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inpStream));

        String line = "";
        boolean comment= true;
        while(comment || line.length()==0 ) {
            line= br.readLine();
            if(line.startsWith("*/")) {
                comment = false;
                line =br.readLine();
            }

            if(line.startsWith("/*")){
                comment = true;
            }
            if(line.length()==0)
                continue;
        }
        String inOrderLine = line;
        String preOrderLine = br.readLine();

        String[] charStr = inOrderLine.split("\\s+");
        Character in[] = new Character[charStr.length];
        for (int i = 0; i < charStr.length; i++)
            in[i] = new Character(charStr[i].charAt(0));

        charStr = preOrderLine.split("\\s+");
        Character pre[] = new Character[charStr.length];
        for (int i = 0; i < charStr.length; i++)
            pre[i] = new Character(charStr[i].charAt(0));

        int len = in.length;
        Node root = tree.buildTree(in, pre, 0, len - 1);
        return root;
    }

    public Node getTree() {
        return root;
    }

    public static Node<Integer> getIntTree()
    {
        Node<Integer> root = new Node<Integer>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        Node node = root.right;
        node.left = new Node(6);
        node.right = new Node(7);
        Node tempNode = node;
        node = node.left;
        node.left = new Node(8);
        node.right = new Node(9);
        node = tempNode.right;
        node.left = new Node(0);
        node.right = new Node(1);

        /*

       1
      / \
     /   \
    /     \
   /       \
   2       3
  / \     / \
 /   \   /   \
 4   5   6   7
        / \ / \
        8 9 0 1

         */

        return root;

    }

    public static Node<Integer> getIntTree1()
    {
        Node<Integer> root = new Node<Integer>(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        Node node = root.right;
        node.left = new Node(6);
        node.right = new Node(7);
        Node tempNode = node;
        node = node.left;
        node.left = new Node(8);
        node.right = new Node(9);
        node = tempNode.right;
        node.left = new Node(1);
        node.right = new Node(1);

        /*

       1
      / \
     /   \
    /     \
   /       \
   2       3
  / \     / \
 /   \   /   \
 4   5   6   7
        / \ / \
        8 9 0 1

         */

        return root;

    }

    /* Given a binary tree, print its nodes according to the
    "bottom-up" postorder traversal. */
    public static void printPostorder(Node node)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.data + " ");
    }

}
