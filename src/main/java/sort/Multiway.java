package sort;

import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/19 15:45
 */
public class Multiway {
    private Multiway() {
    }

    private static void merge(In[] streams) {
        int n = streams.length;
        IndexMinPQ pq = new IndexMinPQ(n);
        for (int i = 0; i < n; i++) {
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
        while (!pq.isEmpty()) {
            StdOut.printf(pq.minKey() + " ");
            int i = pq.delMin();
            if (!streams[i].isEmpty()) {
                pq.insert(i, streams[i].readString());
            }
        }
        StdOut.println();
    }
    /**
     *  Reads sorted text files specified as command-line arguments;
     *  merges them together into a sorted output; and writes
     *  the results to standard output.
     *  Note: this client does not check that the input files are sorted.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int n = args.length;
        In[] streams = new In[n];
        for(int i=0; i<n; i++){
            streams[i] = new In(args[i]);
        }
        merge(streams);
    }
}
