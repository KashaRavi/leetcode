package breadthfirstsearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by rkasha on 22-May-16.
 */
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
        int color;
        List<Edge> adj;

        Vertex(int id) {
            this.id = id;
            adj = new ArrayList<>();
            d = Integer.MAX_VALUE;
            color =-1;//represents undiscovered<==white
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
                int r = 6;
                Edge edge1 = new Edge(y, r);
                graph.get(x).adj.add(edge1);
                Edge edge2 = new Edge(x, r);
                graph.get(y).adj.add(edge2);
            }

            int s = sc.nextInt();
            Vertex source = graph.get(s);
            source.d = 0;
            source.color=0;//mark it as grey.

            Queue<Vertex> queue = new LinkedList<>();
            queue.add(source);


            while (!queue.isEmpty()) {
                Vertex u = queue.poll();
                for (Edge e : u.adj) {
                    Vertex v = graph.get(e.id);
                    if(v.color == -1){
                        v.d = u.d+e.weight;
                        v.color=0;
                        queue.add(v);
                    }
                }
                u.color=1;
            }
            for (int l=1;l<=n;l++) {
                Vertex u = graph.get(l);
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
