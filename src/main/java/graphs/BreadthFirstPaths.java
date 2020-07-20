package graphs;

import fundementals.Queue;
import fundementals.Stack;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 9:34
 */
public class BreadthFirstPaths {

    //marked[v] = is there an s-v path?
    private boolean[] marked;
    //edgeTo[v[ = last edge on s-v path
    private int[] edgeTo;
    //source vertex
    private final int s;

    public BreadthFirstPaths(Graph G, int s){
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        Queue<Integer> queue = new Queue<Integer>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()){
            int v = queue.dequeue();
            for(int adj: G.adj(v)){
                if(!marked[adj]){
                    marked[adj] = true;
                    //保存最短路径上的最后一条边
                    edgeTo[adj] = v;
                    queue.enqueue(adj);
                }
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
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        for(int v=0; v< G.V(); v++){
            if(bfs.hasPathTo(v)){
                StdOut.printf("%d to %d: ", s, v);
                for(Integer x: bfs.pathTo(v)){
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
