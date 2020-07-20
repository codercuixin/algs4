package graphs;

import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 13:17
 * 是二分图吗？
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = true;
    public TwoColor(Graph G){
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for(int s=0; s<G.V(); s++){
            if(!marked[s]){
                dfs(G, s);
            }
        }
    }
    private void dfs(Graph G, int s){
        marked[s] = true;
        for(int w: G.adj(s)){
            if(!marked[w]){
                color[w] = !color[s];
                dfs(G, w);
            }
            //如果s的邻接节点w被标记过了，并且颜色相同，则表示这两个节点颜色相同
            //则说明不是二分图
            else if(color[w] = color[s]){
                isTwoColorable = false;
            }
        }
    }

    public boolean isTwoColorable(){
        return isTwoColorable;
    }
    public static void main(String[] args){
        Graph G = new Graph(new In(args[0]));
        TwoColor cc = new TwoColor(G);
        StdOut.println("Graph is TwoColorable?  The answer is "+cc.isTwoColorable());
    }
}
