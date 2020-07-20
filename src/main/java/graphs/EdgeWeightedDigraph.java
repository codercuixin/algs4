package graphs;

import fundementals.Bag;
import io.In;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/16 9:50
 */
public class EdgeWeightedDigraph {
    private final int V; //顶点总数
    private int E; //边的总数
    private Bag<DirectedEdge>[] adj; //邻接表
    private int[] indegree;             // indegree[v] = indegree of vertex v
    public EdgeWeightedDigraph(int V){
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[])new Bag[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<DirectedEdge>();
        }
    }
    public EdgeWeightedDigraph(In in){
        this.V = in.readInt();
        adj = (Bag<DirectedEdge>[])new Bag[V];
        indegree = new int[V];
        for(int v=0; v<V; v++){
            adj[v] = new Bag<DirectedEdge>();
        }
        int edgeNum =in.readInt();
        while(edgeNum-->0){
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();

            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public int V(){
        return V;
    }
    public int E(){
        return E;
    }

    public void addEdge(DirectedEdge e){
        adj[e.from()].add(e);
        E++;
        indegree[e.to()]++;
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        return indegree[v];
    }

    public Iterable<DirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<DirectedEdge> edges(){
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for(int v=0; v<V; v++){
            for(DirectedEdge e: adj[v]){
                    bag.add(e);
            }
        }
        return bag;
    }
}
