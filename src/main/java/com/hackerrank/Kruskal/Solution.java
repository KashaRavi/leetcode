package com.hackerrank.Kruskal;

/**
 * Created by rkasha on 25-May-16.
 */

import java.io.*;
import java.util.*;

public class Solution {
    final static class Edge {
        int u;
        int v;
        int w;

        Edge(int x, int y, int r) {
            this.u = x;
            this.v = y;
            this.w = r;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int r = sc.nextInt();
            Edge edge = new Edge(x, y, r);
            edges.add(edge);
        }
        int s = sc.nextInt();

        Collections.sort(edges, (o, e) -> {
            if (o.w != e.w) {
                return o.w - e.w;
            } else {
                int oNetWeight = o.u + o.w + o.v;
                int eNetWeight = e.u + e.w + e.v;
                return oNetWeight - eNetWeight;
            }
        });

        List<TreeSet<Integer>> components = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            TreeSet<Integer> component = new TreeSet<>();
            component.add(i + 1);
            components.add(component);
        }

        int totalMSTWeight = 0;
        for (Edge e : edges) {
            int uIndex = findContainerSet(components, e.u);
            int vIndex = findContainerSet(components, e.v);
            if (uIndex != vIndex) {
                totalMSTWeight += e.w;
                components.get(uIndex).addAll(components.get(vIndex));
                components.remove(vIndex);
            }
        }
        System.out.println(totalMSTWeight);
    }

    private static int findContainerSet(List<TreeSet<Integer>> components, Integer u) {
        for (int i = 0; i < components.size(); i++) {
            TreeSet<Integer> component = components.get(i);
            if (component.contains(u)) {
                return i;
            }
        }
        return -1;
    }
}
