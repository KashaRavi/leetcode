package inquiringmanager;

/**
 * Created by rkasha on 05-Jun-16.
 */

import java.io.*;
import java.util.*;

public class Solution {

    static final class Order {
        int type;
        long price;
        long time;

        Order(int type, long price, long time) {
            this.type = type;
            this.price = price;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean isSearchRequired = true;
        long currWindowMax = -1;
        Queue<Order> timeQueue = new LinkedList<Order>();
        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();
            long p = 0;

            if (type == 1) {
                p = sc.nextLong();
            }
            long t = sc.nextLong();

            if (type == 1) {
                Order o = new Order(type, p, t);
                if(!isSearchRequired){
                    if (p >= currWindowMax) {
                        currWindowMax = p;
                        timeQueue.add(o);
                        while (!timeQueue.isEmpty() && (t - timeQueue.peek().time) > 59) {
                            timeQueue.poll();
                        }
                    } else {
                        timeQueue.add(o);
                        while (!timeQueue.isEmpty() && (t - timeQueue.peek().time) > 59) {
                            Order removableOrder = timeQueue.poll();
                            if (removableOrder.price == currWindowMax) {
                                isSearchRequired = true;
                            }
                        }
                    }
                } else{
                    timeQueue.add(o);
                    while (!timeQueue.isEmpty() && (t - timeQueue.peek().time) > 59) {
                        timeQueue.poll();
                    }
                }

            } else {
                while (!timeQueue.isEmpty() && (t - timeQueue.peek().time) > 59) {
                    Order removableOrder = timeQueue.poll();
                    if (!isSearchRequired && removableOrder.price == currWindowMax) {
                        isSearchRequired = true;
                    }
                }
                long price = -1;

                if (timeQueue.isEmpty()) {
                    currWindowMax = -1;
                    isSearchRequired = false;
                    System.out.println(-1);
                } else {
                    if (!isSearchRequired) {
                        System.out.println(currWindowMax);
                    } else {
                        currWindowMax=Collections.max(timeQueue, (o, e) -> {
                            return (int) (o.price - e.price);
                        }).price;
                        isSearchRequired=false;
                        System.out.println(currWindowMax);
                    }
                }
            }

        }
    }

}
