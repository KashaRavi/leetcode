//https://www.geeksforgeeks.org/friends-pairing-problem/
package algorithms.dynamicprogramming.friendspairing;

/**
 * Created by rkasha on 3/9/19.
 */
public class TestClass {
    public static void main(String[] args) {
        int n = 9;
        System.out.println(countFriendsPairings(n));
    }

    //    f(n) = f(n-1) + (n-1)f(n-2)
    public static int countFriendsPairings(int n) {
        int a = 1, b = 2, c = 0;
        if (n <= 2) {
            return n;
        }
        for (int i = 3; i <= n; i++) {
            c = b + (i - 1) * a;
            a = b;
            b = c;
        }
        return c;
    }
}
