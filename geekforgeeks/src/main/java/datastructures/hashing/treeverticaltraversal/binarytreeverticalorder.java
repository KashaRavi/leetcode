package datastructures.hashing.treeverticaltraversal;

import tree.ITreeUtil;
import tree.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by rkasha on 3/4/19.
 */
public class binarytreeverticalorder {

    public static void populateVerticalLevels(Node<Integer> root, int vLevel) {

        if(root == null)
            return;

        if(!vMap.containsKey(vLevel)) {
            vMap.put(vLevel, new ArrayList<Integer>());
        }
        vMap.get(vLevel).add(root.data);

        populateVerticalLevels(root.left, vLevel-1);
        populateVerticalLevels(root.right,vLevel+1);
    }

    static SortedMap<Integer, List<Integer>> vMap = new TreeMap();
    public static void main(String[] args) throws IOException {

        Node<Integer> root = ITreeUtil.getTree(2);
        ITreeUtil.printTree(root);
        populateVerticalLevels(root,0);
        for (int key: vMap.keySet()){
            for(int val: vMap.get(key)) {
                System.out.print(val + "->");
            }
            System.out.println();
        }
    }
}
