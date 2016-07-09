package com.hackerrank.JournetToTheMoon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by rkasha on 04-Jun-16.
 */
public class Solution {

    final static class Vertex {
        int id;
        List<Vertex> adj;
        int color;

        Vertex(int id) {
            this.id = id;
            adj = new ArrayList<>();
            color = -1;
        }
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        //        new Printer().main(null);
        int n = sc.nextInt();
        int p = sc.nextInt();
        List<Vertex> graph = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            graph.add(new Vertex(j));
        }
        for (int i = 0; i < p; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).adj.add(graph.get(v));
            graph.get(v).adj.add(graph.get(u));
        }

        //run BFS to identify the components
        List<Vertex> unvisitedVertices = new ArrayList<>();
        List<Integer> componentSizes = new ArrayList<>();

        long countIsolatedVertices=0;
        for (Vertex v : graph) {
            if(!v.adj.isEmpty()) {
                unvisitedVertices.add(v);
            }else {
                countIsolatedVertices++;
            }
        }

        Queue<Vertex> bfsQueue = new LinkedList<>();
        long totalNonSingleComponents=0;
        while (!unvisitedVertices.isEmpty()) {
            Vertex startVertex = unvisitedVertices.get(0);
            startVertex.color=0;
            bfsQueue.add(startVertex);
            unvisitedVertices.remove(0);
            int componentSize = runBFS(graph, bfsQueue, unvisitedVertices);
            componentSizes.add(componentSize);
            totalNonSingleComponents += componentSize;
        }

        long totalPossiblePairs = 0;

        for (int j = 0; j < componentSizes.size() - 1; j++) {
            for (int k = j + 1; k < componentSizes.size(); k++) {
                totalPossiblePairs += componentSizes.get(j) * componentSizes.get(k);
            }
        }
        totalPossiblePairs += totalNonSingleComponents*countIsolatedVertices+countIsolatedVertices*(countIsolatedVertices-1)/2;

        System.out.println(totalPossiblePairs);

    }

    public static int runBFS(List<Vertex> graph, Queue<Vertex> bfsQueue,
        List<Vertex> unvisitedVertices) {
        int componentSize = 1;
        while (!bfsQueue.isEmpty()) {
            Vertex s = bfsQueue.poll();
            for (Vertex v : s.adj) {
                if (v.color == -1) {
                    v.color = 0;
                    bfsQueue.add(v);
                    componentSize++;
                    unvisitedVertices.remove(v);
                }
            }

        }
        return componentSize;
    }
}
