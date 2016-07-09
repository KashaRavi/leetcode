package minwaitingtime;

/**
 * Created by rkasha on 12-Jun-16.
 */

import java.util.*;

public class Solution {

    static final class Order {
        int arrivalTime;
        int duration;

        Order(int arrivalTime, int duration) {
            this.arrivalTime = arrivalTime;
            this.duration = duration;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Order[] a = new Order[n];
        //queue maintains the list of orders that are eligible to be scheduled in the next iteration.
        Queue<Integer> orderQ = new PriorityQueue<Integer>(11, (o, e) -> {
            return a[o].duration - a[e].duration;
        });

        for (int i = 0; i < n; i++) {
            a[i] = new Order(sc.nextInt(), sc.nextInt());
        }

        Arrays.sort(a, (o, e) -> {
            return o.arrivalTime - e.arrivalTime;
        });

        long nextScheduleTime = 0;
        int i = 0;
        long totalWaitingTime = 0;

        while (true) {
            //determine next Schedule time or end of scheduling
            if (orderQ.isEmpty()) {
                if (i < a.length) {
                    nextScheduleTime = Math.max(nextScheduleTime, a[i].arrivalTime);
                } else {
                    System.out.println(totalWaitingTime / (long) n);
                    return;
                }
            }

            //Add all the jobs till the schedule time
            for (; i < a.length && a[i].arrivalTime <= nextScheduleTime; i++) {
                orderQ.add(i);
            }

            //schedule the next smallest job
            int nextJobInd = orderQ.poll();
            long jobWaitingTime = nextScheduleTime + (long) (a[nextJobInd].duration - a[nextJobInd].arrivalTime);
            totalWaitingTime += jobWaitingTime;
            nextScheduleTime += a[nextJobInd].duration;
        }
    }
}
