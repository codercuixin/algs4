package graphs;

import fundementals.Bag;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 11:03
 * G是无环图吗？（假设不存在自环或平行边）
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;
    public Cycle(Graph G){
        marked = new boolean[G.V()];
        for(int v=0; v<G.V(); v++){
            //测试所有可能的联通分量
            if(!marked[v]){
                dfs(G, v, v);
            }
        }
    }

    /**
     *
     * @param G
     * @param v v代表u的下一个邻接节点。
     * @param u u代表v的前一个访问节点。
     */
    private void dfs(Graph G, int v, int u){
        marked[v] = true;
        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w, v);
                //w已经访问过，但不是v的前节点，表示存在环。
            }else if( w != u){
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle(){
        return hasCycle;
    }

    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        Cycle cc = new Cycle(G);
       StdOut.println("Graph has cycle?  The answer is "+cc.hasCycle);
    }
}
