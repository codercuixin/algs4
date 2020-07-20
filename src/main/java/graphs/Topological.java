package graphs;

import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 14:16
 */
public class Topological {
    private Iterable<Integer> order;
    public Topological(Digraph G){
        DirectedCycle cycle = new DirectedCycle(G);
        if(!cycle.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
        }
    }
    public Iterable<Integer> order(){
        return  order;
    }
    public boolean isDAG(){
        return order!=null;
    }
    public static void main(String[] args){
        String filename =args[0];
        String separator = args[1];
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological topological =new Topological(sg.G());

        for(int v: topological.order()){
            StdOut.println(sg.name(v));
        }
    }
}
