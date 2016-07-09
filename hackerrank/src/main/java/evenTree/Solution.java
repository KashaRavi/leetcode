package evenTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by rkasha on 28-May-16.
 */
public class Solution {
    final static class Vertex{
        int id;
        boolean isVisited;
        List<Vertex> adj;
        Vertex(int id){
            this.id=id;
            adj = new ArrayList<Vertex>();
            isVisited = false;
        }
    }

    static int ans =0;
    public static void main(String[]args){
        Scanner sc =new Scanner(System.in);
        int n =  sc.nextInt();

        Vertex[] vertices = new  Vertex[n+1];

        for(int i=1;i<=n;i++){
            vertices[i] = new Vertex(i);
        }

        int  m   = sc.nextInt();

        for(int i=0;i<m;i++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            vertices[x].adj.add(vertices[y]);
            vertices[y].adj.add(vertices[x]);
        }

        dfs(vertices[1]);
        System.out.println(ans);
    }

    public static int dfs(Vertex v){
        v.isVisited =true;
        int num_vertices=0;
        for(int i=0;i<v.adj.size();i++){
            Vertex child= v.adj.get(i);
            if(!child.isVisited) {
                int subTreeSize = dfs(child);
                if(subTreeSize%2==0){
                    ans++;
                } else {
                    num_vertices += subTreeSize;
                }
            }
        }
        num_vertices +=1;
        return num_vertices;
    }
}
