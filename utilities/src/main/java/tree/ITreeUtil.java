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
import java.util.HashMap;
import java.util.Map;

public class ITreeUtil {
    static int preIndex = 0;
    static Map<Integer, Node<Integer>> idToTreeMap = new HashMap<Integer, Node<Integer>>();
    static Map<Integer, String> idToTreeStrMap = new HashMap<Integer, String>();
    static String filename = "binary_integer_tree_samples.txt";

    public static void main(String args[]) throws IOException {
        Node root = getTree(0);
        System.out.println(getTreeStr(0));
        System.out.print("Inorder traversal: ");
        printInorder(root);
    }

    public static Node getTree(int id) throws IOException {
        loadTrees(filename);
        return idToTreeMap.get(id);
    }

    public static String getTreeStr(int id) throws IOException {
        loadTrees(filename);
        return idToTreeStrMap.get(id);
    }

    public static void printTree(int id) throws IOException {
        System.out.println(getTreeStr(id));
    }

    public static void printTree(final Node<Integer> root) throws IOException {
        for(Map.Entry<Integer,Node<Integer>> entry: idToTreeMap.entrySet()){
            if(entry.getValue() == root) {
                System.out.println(getTreeStr(entry.getKey()));
                return;
            }
        }
        System.out.println("No tree found");
    }


    /**
     * Prints tree with only single character values in a proper way
     */
    public static void printTree(Node root, boolean isNotPersisted) {
        BTreePrinter.printNode(root);
   }

    public static void loadTrees(String filename) throws IOException {
        if (idToTreeMap == null || idToTreeMap.size() == 0) {
            InputStream inpStream = ReadJavaProperties.class.getClassLoader()
                    .getResourceAsStream(filename);

            BufferedReader br = new BufferedReader(new InputStreamReader(inpStream));

            while (constructTree(br) != null)
                ;
            br.close();
        }
    }

    public static Node constructTree(BufferedReader br) throws IOException {
        String line = "";
        boolean comment = true;
        String treeLines = "";
        while (comment || line.length() == 0) {
            line = br.readLine();

            if (line == null)
                return null;

            if (line.startsWith("*/")) {
                comment = false;
                line = br.readLine();
            }

            if (line.indexOf("[START]") != -1) {
                line = br.readLine();
                while (line.indexOf("[END]") == -1) {
                    treeLines = treeLines + "\n" + line;
                    line = br.readLine();
                }
            }

            if (line.startsWith("/*")) {
                comment = true;
            }
            if (line.length() == 0)
                continue;
        }

        String[] idStrs = line.split(":");
        int id = Integer.parseInt(idStrs[1]);

        String inOrderLine = br.readLine();
        String preOrderLine = br.readLine();

        String[] charStr = inOrderLine.split("\\s+");
        int[] in = new int[charStr.length];
        for (int i = 0; i < charStr.length; i++)
            in[i] = Integer.parseInt(charStr[i]);

        charStr = preOrderLine.split("\\s+");
        int[] pre = new int[charStr.length];
        for (int i = 0; i < charStr.length; i++)
            pre[i] = Integer.parseInt(charStr[i]);

        preIndex = 0;
        int len = in.length;
        Node<Integer> root = buildTree(in, pre, 0, len - 1);
        idToTreeMap.put(id, root);
        idToTreeStrMap.put(id, treeLines);
        return root;
    }

    static Node buildTree(int[] in, int[] pre, int inStrt, int inEnd) {
        if (inStrt > inEnd)
            return null;

        Node<Integer> tNode = new Node<Integer>(pre[preIndex++]);

        if (inStrt == inEnd)
            return tNode;

        int inIndex = search(in, inStrt, inEnd, tNode.data);

        tNode.left = buildTree(in, pre, inStrt, inIndex - 1);
        tNode.right = buildTree(in, pre, inIndex + 1, inEnd);

        return tNode;
    }

    static int search(int[] arr, int strt, int end, int value) {
        int i;
        for (i = strt; i <= end; i++) {
            if (arr[i] == value)
                return i;
        }
        return i;
    }

    public static void printInorder(Node node) {
        if (node == null)
            return;

        printInorder(node.left);
        System.out.print(node.data + " ");
        printInorder(node.right);
    }

    public static void printPostorder(Node node) {
        if (node == null)
            return;

        printPostorder(node.left);
        printPostorder(node.right);
        System.out.print(node.data + " ");
    }

    public static void printPreorder(Node node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }


}
