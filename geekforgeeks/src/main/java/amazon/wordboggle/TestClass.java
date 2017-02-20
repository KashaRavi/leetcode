//http://www.practice.geeksforgeeks.org/problem-page.php?pid=1653

package amazon.wordboggle;

import java.util.Scanner;

public class TestClass {
    private static final int SIZE = 26;
    private static int M = 0;
    private static int N = 0;

    public static void main(String[] args) {
        readFromStdin();

    }


    static void findMatchingWords(String[] dictionary, char[][] boggle) {
        TrieNode root = new TrieNode();
        for (String word : dictionary) {
            insert(root, word);
        }
        M = boggle.length;
        N = boggle[0].length;
        findWords(boggle, root);
    }

    private static void readFromStdin() {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();
            sc.nextLine();
            String dictionaryStr = sc.nextLine();
            String[] words = dictionaryStr.split(" ");
            TrieNode root = new TrieNode();
            for (String word : words) {
                insert(root, word);
            }
            int m = sc.nextInt();
            int n = sc.nextInt();
            M = m;
            N = n;
            sc.nextLine();
            char[][] boggle = new char[m][n];
            for (int u = 0; u < m; u++) {
                for (int v = 0; v < n; v++) {
                    while (sc.hasNext(".")) {
                        char c = sc.next(".").charAt(0);
                        if (c == ' ') {
                            continue;
                        }
                        boggle[u][v] = c;
                    }
                }
            }
        }
    }

    private static void findWords(char[][] boggle, TrieNode root) {
        boolean[][] visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (root.children[getIndex(boggle[i][j])] != null) {
                    String str = "" + boggle[i][j];
                    searchWord(root.children[getIndex(boggle[i][j])], boggle, i, j, visited, str);
                }
            }
        }
    }

    /**
     * It checks if the next char on dfs path is available as an adjacent character of the of the current char and if it available advance
     * the root to next char, add the next char to the str, advance i & j in the direction of next char and make a recursive call.
     * <p>
     * Its basically checking if the string constructed so far is a word in the dictionary and printing if so.
     * It essentially is traversing the trie in a dfs fashion, at each level essentially verifying if the next char is also an adjacent char in the matrix.
     *
     * @param root    The root pointing to the last character of the path visited so far in the trie and is the current char.
     * @param boggle  boggle[i][j] represents the current char.
     * @param i       represents current row of the matrix
     * @param j       represents the current column of the matrix
     * @param visited visited matrix marks all the characters of the path as visited
     * @param str     str contains all the characters of the path visited so far including the current char
     */
    private static void searchWord(TrieNode root, char[][] boggle, int i, int j, boolean[][] visited, String str) {
        if (root.isLeaf) {
            System.out.println(str);
        }
        visited[i][j] = true;
        for (int k = 0; k < SIZE; k++) {
            if (root.children[k] != null) {
                char c = (char) ((char) k + 'A');
                if (isSafe(i + 1, j + 1, visited) && boggle[i + 1][j + 1] == c) {
                    searchWord(root.children[k], boggle, i + 1, j + 1, visited, str + c);
                } else if (isSafe(i, j + 1, visited) && boggle[i][j + 1] == c) {
                    searchWord(root.children[k], boggle, i, j + 1, visited, str + c);
                } else if (isSafe(i - 1, j + 1, visited) && boggle[i - 1][j + 1] == c) {
                    searchWord(root.children[k], boggle, i - 1, j + 1, visited, str + c);
                } else if (isSafe(i + 1, j, visited) && boggle[i + 1][j] == c) {
                    searchWord(root.children[k], boggle, i + 1, j, visited, str + c);
                } else if (isSafe(i + 1, j - 1, visited) && boggle[i + 1][j - 1] == c) {
                    searchWord(root.children[k], boggle, i + 1, j - 1, visited, str + c);
                } else if (isSafe(i, j - 1, visited) && boggle[i][j - 1] == c) {
                    searchWord(root.children[k], boggle, i, j - 1, visited, str + c);
                } else if (isSafe(i - 1, j - 1, visited) && boggle[i - 1][j - 1] == c) {
                    searchWord(root.children[k], boggle, i - 1, j - 1, visited, str + c);
                } else if (isSafe(i - 1, j, visited) && boggle[i - 1][j] == c) {
                    searchWord(root.children[k], boggle, i - 1, j, visited, str + c);
                }
            }
            visited[i][j] = false;
        }
    }

    /**
     * This method assumes that the trie is made from characters A-Z only
     */
    private static void insert(TrieNode root, String word) {
        int n = word.length();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int index = getIndex(c);
            if (root.children[index] == null) {
                root.children[index] = new TrieNode();
            }
            root = root.children[index];
        }
        root.isLeaf = true;
    }

    private static int getIndex(char c) {
        return c - (int) 'A';
    }

    private static boolean isSafe(int i, int j, boolean visited[][]) {
        return (i >= 0 && i < M && j >= 0 && j < N && !visited[i][j]);
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isLeaf;

        TrieNode() {
            children = new TrieNode[TestClass.SIZE];
            for (int i = 0; i < TestClass.SIZE; i++) {
                children[i] = null;
            }
            isLeaf = false;
        }
    }
}



