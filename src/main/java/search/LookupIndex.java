package search;

import fundementals.Queue;
import io.In;
import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/3 10:25
 */
public class LookupIndex {
    private LookupIndex(){}

    public static void main(String[] args){
        String fileName = args[0];
        String separator = args[1];
        In in = new In(fileName);
        ST<String, Queue<String>> st = new ST<String, Queue<String>>();
        ST<String, Queue<String>> ts = new ST<String, Queue<String>>();

        while(in.hasNextLine()){
            String line = in.readLine();
            String[] fields = line.split(separator);
            String key = fields[0];
            for(int i=1; i<fields.length; i++){
                String val = fields[i];
                if(!st.contains(key))st.put(key, new Queue<String>());
                if(!ts.contains(val)) ts.put(val, new Queue<String>());
                st.get(key).enqueue(val);
                ts.get(val).enqueue(key);
            }
        }
        StdOut.println("Done indexing");

        //read queries from standard input, one per line
        while(!StdIn.isEmpty()){
            String query = StdIn.readLine();
            if(st.contains(query)){
                for(String vals: st.get(query)){
                    StdOut.println(" "+vals);
                }
            }
            if(ts.contains(query)){
                for(String keys: ts.get(query)){
                    StdOut.println(" "+keys);
                }
            }
        }
    }
}
