package hiring1.components;

import java.util.*;

/**
 * Created by Ravi Kasha on 10-07-2016.
 */

public class TestClass {

    final static class Edge {
        int id;

        Edge(int id) {
            this.id = id;
        }
    }

    final static class Vertex {
        int id;
        int d;
        int color;
        int knowledge;
        List<Edge> adj;

        Vertex(int id, int knowledge) {
            this.id = id;
            adj = new ArrayList<Edge>();
            d = Integer.MAX_VALUE;
            color = -1;//represents undiscovered<==white
            this.knowledge = knowledge;
        }
    }

    public static void main(String[] args) {
        int constant = 1000000007;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        List<Vertex> graph = new ArrayList<Vertex>();
        graph.add(new Vertex(0, 0));
        for (int j = 1; j <= n; j++) {
            int knowledge = sc.nextInt();
            graph.add(new Vertex(j, knowledge));
        }

        for (int j = 0; j < m; j++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Edge edge1 = new Edge(y);
            graph.get(x).adj.add(edge1);
            Edge edge2 = new Edge(x);
            graph.get(y).adj.add(edge2);
        }

        Queue<Vertex> queue = new LinkedList<Vertex>();
        long gameCount = 1;
        List<Integer> leaders = new ArrayList<Integer>();
        for (int p = 1; p <= n; p++) {
            Vertex source = graph.get(p);
            if (source.color == -1) {
                int maxKnowledge = -1;
                int maxKnowledgePersons = 0;
                source.color = 0;//mark it as grey.
                queue.add(source);
                while (!queue.isEmpty()) {
                    Vertex u = queue.poll();
                    if (u.knowledge > maxKnowledge) {
                        maxKnowledge = u.knowledge;
                        maxKnowledgePersons = 1;
                    } else if (u.knowledge == maxKnowledge) {
                        maxKnowledgePersons++;
                    }
                    for (Edge e : u.adj) {
                        Vertex v = graph.get(e.id);
                        if (v.color == -1) {
                            v.color = 0;
                            queue.add(v);
                        }
                    }
                    u.color = 1;
                }
                leaders.add(maxKnowledgePersons);
                gameCount = ((gameCount * (long) maxKnowledgePersons) % constant);
            }
        }
        System.out.println(gameCount);
    }
}
