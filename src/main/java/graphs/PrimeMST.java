package graphs;

import fundementals.Queue;
import io.In;
import io.StdOut;
import sort.IndexMinPQ;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/15 14:32
 */
public class PrimeMST {
    private Edge[] edgeTo; //距离树最近的边
    private double[] distTo; //distTo[w] = edgeTo[w].weight
    private boolean[] marked; //如果v在树中则为true
    private IndexMinPQ<Double> pq; //有效的横切边

    public PrimeMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<Double>(G.V());
        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        //将顶点v添加到树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue; // v-w失效
            if (e.weight() < distTo[w]) {
                //连接w和树的最佳边Edge变为e
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> res = new Queue<Edge>();
        for (int i = 0; i < edgeTo.length; i++) {
            if(edgeTo[i]!=null){
                res.enqueue(edgeTo[i]);
            }
        }
        return res;
    }

    public double weight() {
        double sum = 0.0;
        for (Edge e : edges()) {
            sum += e.weight();
        }
        return sum;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        PrimeMST mst = new PrimeMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.println(mst.weight());
    }
}
