package graphs;

import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/9 14:18
 */
public class DepthFirstSearch {
    //marked[v] = is there an s-v path?
    private boolean[] marked;
    //number of vertices connected to s
    private int count;

    public DepthFirstSearch(Graph G, int s){
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v){
        count++;
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }
    /**
     * Returns the number of vertices connected to the source vertex {@code s}.
     * @return the number of vertices connected to the source vertex {@code s}
     */
    public int count() {
        return count;
    }


    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for(int v=0; v< G.V(); v++){
            if(search.marked(v)){
                StdOut.print(v+" ");
            }
        }
        StdOut.println();
        if(search.count() != G.V()){
            StdOut.println("Not connected");
        }else{
            StdOut.println("connected");
        }
    }
}
