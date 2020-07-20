package graphs;

import fundementals.Bag;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 10:21
 */
public class CC {
    //记录是否访问过顶点
    private boolean marked[];
    //记录顶点所在的联通分量
    private int[] id;
    //第几个联通分量
    private int count;
    public CC(Graph G){
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            if(!marked[v]){
                dfs(G, v);
                count++;
            }
        }
    }
    private void dfs(Graph G, int v){
        marked[v] = true;
        id[v] = count;
        for(int adj: G.adj(v) ){
            if(!marked[adj]){
                marked[adj] = true;
                dfs(G, adj);
            }
        }
    }


    public boolean connected(int v, int w){
        return id[v] == id[w];
    }

    /**
     * 返回v所在的component
     * @param v
     * @return
     */
    public int id(int v){
        return id[v];
    }
    public int count(){
        return count;
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        CC cc = new CC(G);
        int M = cc.count();
        StdOut.println(M + " components");

        Bag<Integer>[] components = ( Bag<Integer>[]) new Bag[M];
        for(int i=0; i<M; i++){
            components[i] = new Bag<Integer>();
        }
        for(int v=0; v<G.V(); v++){
            components[cc.id(v)].add(v);
        }
        //print components
        for(int i=0; i<M; i++){
            for(int v: components[i]){
                StdOut.print(v+" ");
            }
            StdOut.println();
        }
    }
}
