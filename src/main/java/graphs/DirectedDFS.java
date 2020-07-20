package graphs;

import fundementals.Bag;
import io.In;
import io.StdOut;

import java.util.Iterator;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 10:26
 */
public class DirectedDFS {
    private boolean marked[];
    public DirectedDFS(Digraph G, int s){
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    public DirectedDFS(Digraph G, Iterable<Integer> sources){
        marked = new boolean[G.V()];
        for(int s: sources){
            if(!marked[s]){
                dfs(G, s);
            }
        }
    }
    private void dfs(Digraph G, int s){
        marked[s] =true;
        for(int w: G.adj(s)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
    }
    public boolean marked(int s){
        return marked[s];
    }

    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));
        Bag<Integer> bag = new Bag<Integer>();
        for(int i=1; i<args.length; i++){
            bag.add(Integer.parseInt(args[i]));
        }
        DirectedDFS dfs = new DirectedDFS(G, bag);
        for(int v=0; v<G.V(); v++){
            if(dfs.marked(v)){
                StdOut.print(v+" ");
            }
        }
        StdOut.println();
    }
}
