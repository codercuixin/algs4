package graphs;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/17 11:17
 */
public class CPM {
    public static void main(String[] args){
        int N = StdIn.readInt();
        StdIn.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2*N +2);
        int s = 2 *N, t= 2*N+1;
        for(int i=0; i<N; i++){
            String[] a = StdIn.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            //i+N表示起始顶点对应的结束顶点
            //起始顶点到结束顶点对应的边
            G.addEdge(new DirectedEdge(i, i+N, duration));
            //起点到起始顶点对应的边
            G.addEdge(new DirectedEdge(s, i, 0.0));
            //结束顶点到终点对应的边
            G.addEdge(new DirectedEdge(i+N, t, 0.0));

            for(int j=1; j<a.length; j++){
                int successor = Integer.parseInt(a[j]);
                //优先级限制条件对应的边
                G.addEdge(new DirectedEdge(i+N, successor, 0.0));
            }
        }
        ACyclicLP aCyclicLP = new ACyclicLP(G, s);
        StdOut.println("Start times:");
        for(int i=0; i<N; i ++){
            StdOut.printf("%4d: %5.1f\n", i, aCyclicLP.distTo(i));
        }
        StdOut.printf("Finish time: %5.1f\n", aCyclicLP.distTo(t));
    }
}
