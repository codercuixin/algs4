package graphs;

import fundementals.Queue;
import io.In;
import io.StdOut;
import sort.MinPQ;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/15 13:16
 */
public class LazyPrimeMST {
    private boolean[] marked; //最小生成树的顶点
    private Queue<Edge> mst; //最小生成树的边
    private MinPQ<Edge> pq; //横切边（包括失效的边）

    public LazyPrimeMST(EdgeWeightedGraph G){
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        visit(G, 0);
        while (!pq.isEmpty()){
            Edge e = pq.delMin(); //从pq中得到权重最小的边
            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]) continue; //跳过失效的边
            mst.enqueue(e); //将边添加到树中
            if(!marked[v]) visit(G, v);//将顶点（w或v）添加到树中
            if(!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        //标记顶点v并将所有连接v和未被标记顶点的边加入pq
        marked[v] = true;
        for(Edge edge: G.adj(v)){
            if(!marked[edge.other(v)]){
                pq.insert(edge);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        double sum = 0.0;
        for(Edge edge: edges()){
            sum += edge.weight();
        }
        return sum;
    }

    public static void main(String[] args){
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        LazyPrimeMST mst = new LazyPrimeMST(G);
        for(Edge e: mst.edges()){
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
