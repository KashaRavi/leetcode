package datastructures.graphs;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by rkasha on 5/8/19.
 */
public class Graph {

    public int V;   // No. of vertices
    public LinkedList<Integer> adj[]; // Adjacency List

    Graph(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v,int w) { adj[v].add(w); }


    public static Graph getGraph() {
        // Create a graph given in the above diagram
        Graph g = new Graph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        return g;
    }
}



