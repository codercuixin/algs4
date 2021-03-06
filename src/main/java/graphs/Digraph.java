package graphs;

import fundementals.Bag;
import fundementals.Stack;
import io.In;
import io.StdOut;

import java.util.NoSuchElementException;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/9 11:20
 */
public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;        // indegree[v] = indegree of vertex v
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges
     * param V the number of vector
     */
    public Digraph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[] )new Bag[V];
        for(int v=0; v<V;v++){
            adj[v] = new Bag<Integer>();
        }
    }
    public Digraph(In in){
        if (in == null) throw new IllegalArgumentException("argument is null");
        try {
            this.V = in.readInt();
            indegree = new int[V];
            if (V < 0) throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.readInt();
            if(E<0) throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
            for(int i=0; i<E; i++){
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }catch (NoSuchElementException e){
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

    public Digraph(Digraph G){
        this.V = G.V();
        this.E = G.E();
        if(V < 0) throw new IllegalArgumentException("Number of vertices must be non negative");
        indegree = new int[V];
        for (int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        //update adjacency lists
        adj = (Bag<Integer>[])new Bag[V];
        for(int v = 0; v<V; v++){
            adj[v] = new Bag<Integer>();
        }

        for(int v=0;v<G.V(); v++){
            //reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for(int w: G.adj[v]){
                reverse.push(w);
            }
            for(int w: reverse){
                adj[v].add(w);
            }
        }
    }
    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w){
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        indegree[w]++;
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }


    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWLINE);
        for(int v=0; v<V; v++){
            s.append(v).append(": ");
            for(int w:adj[v]){
                s.append(w).append(" ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for(int v=0; v<this.V; v++){
            for(int w: adj(v)){
                R.addEdge(w, v);
            }
        }
        return R;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        StdOut.println(G);
    }
}
