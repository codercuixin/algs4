package graphs;

import fundementals.Stack;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/9 14:51
 */
public class DepthFirstDirectedPaths {
    //marked[v] = is there an s-v path?
    private boolean[] marked;
    //edgeTo[v[ = last edge on s-v path
    private int[] edgeTo;
    //source vertex
    private final int s;

    public DepthFirstDirectedPaths(Digraph G, int s){
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    //depth first search form v
    private void dfs(Digraph G, int v){
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v){
        validateVertex(v);
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        validateVertex(v);
        if(!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x =v ; x!=s; x=edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);
        for(int v=0; v< G.V(); v++){
            if(dfs.hasPathTo(v)){
                StdOut.printf("%d to %d: ", s, v);
                for(Integer x: dfs.pathTo(v)){
                    if(x == s) StdOut.print(x);
                    else StdOut.print("-"+x);
                }
                StdOut.println();
            }else{
                StdOut.printf("%d to %d not connected\n", s, v);
            }
        }
    }
}
