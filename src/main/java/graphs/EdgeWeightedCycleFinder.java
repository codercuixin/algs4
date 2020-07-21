package graphs;

import fundementals.Stack;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 13:20
 * todo 查错
 */
public class EdgeWeightedCycleFinder {

    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    /**
     * 有向环中的所有顶点（如果存在）
     */
    private Stack<DirectedEdge> cycle;
    /**
     * 递归调用的栈上的所有顶点
     */
    private boolean[] onStack;

    public EdgeWeightedCycleFinder(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onStack = new boolean[G.V()];
        //测试所有可能的开始节点
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s);
            }
        }
    }

    /**
     * 深度优先访问，标记从s可达的顶点
     */
    private void dfs(EdgeWeightedDigraph G, int s) {
        marked[s] = true;
        onStack[s] = true;
        for (DirectedEdge edge : G.adj(s)) {
            int w = edge.to();
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = edge;
                dfs(G, w);
            } else if (onStack[w]) {
                //如果s的邻接节点w已经被标记过，并且在这一次深度优先访问中（栈上）
                //则表明存在有向环
                cycle = new Stack<DirectedEdge>();
                for (DirectedEdge x = edgeTo[edge.from()]; x!=null && x != edge; x = edgeTo[x.from()] ) {
                    cycle.push(x);
                }
                cycle.push(edge);
            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        EdgeWeightedCycleFinder cc = new EdgeWeightedCycleFinder(G);
        StdOut.println("Graph has cycle?  The answer is " + cc.hasCycle());
        if (cc.hasCycle()) {
            for (DirectedEdge i : cc.cycle()) {
                StdOut.println(i + " ");
            }
            StdOut.println();
        }
    }
}
