package graphs;

import fundementals.Stack;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 13:20
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    /**
     * 有向环中的所有顶点（如果存在）
     */
    private Stack<Integer> cycle;
    /**
     * 递归调用的栈上的所有顶点
     */
    private boolean[] onStack;

    public DirectedCycle(Digraph G){
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        //测试所有可能的开始节点
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs(G, s);
            }
        }
    }

    /**
     * 深度优先访问，标记从s可达的顶点
     */
    private void dfs(Digraph G, int s){
        marked[s] = true;
        onStack[s] = true;
        for(int w: G.adj(s)){
            if(this.hasCycle())return;
            else if(!marked[w]){
                edgeTo[w] =s;
                dfs(G, w);
            }else if(onStack[w]){
                //如果s的邻接节点w已经被标记过，并且在这一次深度优先访问中（栈上）
                //则表明存在有向环
                cycle = new Stack<Integer>();
                cycle.push(w);
                for(int x=s; x!=w; x=edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
            }
        }
        onStack[s] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }
    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args){
        Digraph G = new Digraph(new In(args[0]));
        DirectedCycle cc = new DirectedCycle(G);
        StdOut.println("Graph has cycle?  The answer is "+cc.hasCycle());
        if(cc.hasCycle()){
            for(int i: cc.cycle()){
                StdOut.print(i+" ");
            }
            StdOut.println();
        }
    }
}
