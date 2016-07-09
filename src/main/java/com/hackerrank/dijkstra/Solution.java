package com.hackerrank.dijkstra;

/**
 * Created by rkasha on 22-May-16.
 */

import com.hackerrank.printFile.Printer;

import java.io.*;
import java.util.*;

public class Solution {

    final static class Edge {
        int id;
        int weight;

        Edge(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    final static class Vertex {
        int id;
        int d;
        List<Edge> adj;

        Vertex(int id) {
            this.id = id;
            adj = new ArrayList<>();
            d = Integer.MAX_VALUE;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        new Printer().main(null);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<Vertex> graph = new ArrayList<>();
            for (int j = 0; j <= n; j++) {
                graph.add(new Vertex(j));
            }

            for (int j = 0; j < m; j++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int r = sc.nextInt();
                Edge edge1 = new Edge(y, r);
                graph.get(x).adj.add(edge1);
                Edge edge2 = new Edge(x, r);
                graph.get(y).adj.add(edge2);
            }

            int s = sc.nextInt();
            graph.get(s).d = 0;

            PriorityQueue<Vertex> minQ = new PriorityQueue<>(n, (o, e) -> {
                return o.d - e.d;
            });

            for (int k = 1; k <= n; k++) {
                minQ.add(graph.get(k));
            }

            SortedSet<Vertex> visitedSet = new TreeSet<>((o, e) -> {
                return o.id - e.id;
            });

            while (!minQ.isEmpty()) {
                Vertex u = minQ.poll();
                visitedSet.add(u);
                for (Edge e : u.adj) {
                    Vertex v = graph.get(e.id);
                    if (u.d != Integer.MAX_VALUE && (e.weight + u.d < v.d)) {
                        minQ.remove(v);
                        v.d = e.weight + u.d;
                        minQ.add(v);
                    }
                }
            }
            for (Vertex u : visitedSet) {
                if (u.id != s) {
                    int d = u.d;
                    if (u.d == Integer.MAX_VALUE)
                        d = -1;
                    System.out.print(d + " ");
                }
            }
            System.out.println();
        }
    }
}
