package recursion;

/**
 * @author rkasha
 */
public class CoinChange {

    public static void main(String[] args){
        CoinChange coinChange= new CoinChange();
        System.out.println(coinChange.countWays(100));
        System.out.println(coinChange.countWays(2));
        System.out.println(coinChange.countWays(3));
        System.out.println(coinChange.countWays(4));
        System.out.println(coinChange.countWays(5));
        System.out.println(coinChange.countWays(6));
        System.out.println(coinChange.countWays(7));
        System.out.println(coinChange.countWays(10));
    }

    private int countWays(int n){
        return countWaysDP(n,25);
    }

    public int countWaysDP(int n, int denom) {
        int next_denom=denom;
        switch (denom){
        case 25:
            next_denom=10;
            break;
        case 10:
            next_denom=5;
            break;
        case 5:
            next_denom=1;
            break;
        case 1:
            return 1;
        }
        
        int numWays=0;
        for(int i=0;i*denom <= n;i++){
            System.out.println("("+i+"*denom)");
            numWays+=countWaysDP(n-i*denom,next_denom);
        }
        return numWays;
    }
}
