package graphs;

import fundementals.Bag;
import io.In;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/15 10:17
 */
public class EdgeWeightedGraph {
    private final int V;//顶点总数
    private int E;//边的总数
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[])new Bag[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<Edge>();
        }
    }

    public EdgeWeightedGraph(In in){
        this.V = in.readInt();
        adj = (Bag<Edge>[])new Bag[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<Edge>();
        }
        int edgeNum =in.readInt();
        while(edgeNum-->0){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();

            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }

    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v){
        return adj[v];
    }

    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<Edge>();
        for(int v=0; v<V; v++){
            for(Edge e: adj[v]){
                //确保只讲边加入bag一次
                if(e.other(v) > v){
                    b.add(e);
                }
            }
        }
        return b;
    }
}
