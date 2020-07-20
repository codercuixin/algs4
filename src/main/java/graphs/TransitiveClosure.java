package graphs;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 15:48
 */
public class TransitiveClosure {
    private DirectedDFS[] all;
    TransitiveClosure(Digraph G){
        all = new DirectedDFS[G.V()];
        for(int v=0; v<G.V(); v++){
            all[v] = new DirectedDFS(G, v);
        }
    }
    boolean reachable(int v, int w){
        return all[v].marked(w);
    }
}
