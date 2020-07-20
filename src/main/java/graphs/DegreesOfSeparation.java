package graphs;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 14:51
 */
public class DegreesOfSeparation {
    public static void main(String[] args){
        SymbolGraph symbolGraphs = new SymbolGraph(args[0], args[1]);
        Graph G = symbolGraphs.G();
        String source = args[2];
        if(!symbolGraphs.contains(source)){
           StdOut.println(source + " not in database.");
            return;
        }
        int sourceIndex = symbolGraphs.index(source);
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(G, sourceIndex);
        while(!StdIn.isEmpty()){
            String dest = StdIn.readLine();
            if(symbolGraphs.contains(dest)){
                int destIndex = symbolGraphs.index(dest);
                if(breadthFirstPaths.hasPathTo(destIndex)){
                    for(int linkIndex: breadthFirstPaths.pathTo(destIndex)){
                        StdOut.print(symbolGraphs.name(linkIndex) +" ");
                    }
                    StdOut.println();
                }else{
                    StdOut.println("Not connected");
                }
            }else{
                StdOut.println("Not in database");
            }
        }
    }
}
