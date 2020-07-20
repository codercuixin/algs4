package graphs;

import fundementals.Stack;
import io.In;
import io.StdOut;
import sort.IndexMinPQ;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/17 9:39
 * shortest paths in a DAG
 */
public class ACyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public ACyclicSP(EdgeWeightedDigraph G, int s){
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for(int v=0; v<G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        TopologicalX topologicalX = new TopologicalX(G);
        for(int v: topologicalX.order()){
            relax(G, v);
        }
    }
    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e: G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    public Iterable<DirectedEdge> pathTo(int v){
        if(!hasPathTo(v)) return null;
        Stack<DirectedEdge> paths = new Stack<DirectedEdge>();
        for(DirectedEdge e = edgeTo[v]; e!=null; e= edgeTo[e.from()]){
            paths.push(e);
        }
        return paths;
    }

    public static void main(String[] args){
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        ACyclicSP sp = new ACyclicSP(G, s);
        for(int t=0; t<G.V(); t++){
            StdOut.print(s+" to "+t);
            StdOut.printf(" (%4.2f): ", sp.distTo(t));
            if(sp.hasPathTo(t)){
                for(DirectedEdge e: sp.pathTo(t)){
                    StdOut.print(e+" ");
                }
            }
            StdOut.println();
        }
    }
}
