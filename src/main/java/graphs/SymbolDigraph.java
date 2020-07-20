package graphs;

import io.In;
import io.StdIn;
import io.StdOut;
import search.ST;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/10 14:18
 *  符号图（顶点为符号的图，利用hash数据结构将符号映射为顶点计数）
 */
public class SymbolDigraph {
    //符号名->顶点索引
    private ST<String, Integer> st;
    //顶点索引->符号名
    private String[] keys;
    private Digraph G;

    public SymbolDigraph(String stream, String separator){
        st = new ST<String, Integer>();
        //第一遍读取流，构建索引和反向索引
        In in = new In(stream);
        while(in.hasNextLine()){
            String[] a = in.readLine().split(separator);
            for(int i=0; i<a.length;i++){
                if(!st.contains(a[i])){
                    st.put(a[i], st.size());
                }
            }
        }
        //构建反向索引
        keys = new String[st.size()];
        for(String key: st.keys()){
            keys[st.get(key)] = key;
        }

        //第二遍读取流，构建图
        in = new In(stream);
        G = new Digraph(st.size());
        while(in.hasNextLine()){
            String[] a = in.readLine().split(separator);
            int v = st.get(a[0]);
            for(int i= 1; i<a.length; i++){
                int w = st.get(a[i]);
                G.addEdge(v, w);
            }
        }
    }

    /**
     * 判断是否含有该符号名
     */
    public boolean contains(String symbol){
        return st.contains(symbol);
    }

    /**
     * 返回符号名对应的顶点索引
     */
    public int index(String symbol){
        return st.get(symbol);
    }

    /**
     * 返回顶点索引对应的符号名
     */
    public String name(int i){
        return keys[i];
    }

    /**
     * 返回隐藏的Graph对象
     */
    public Digraph G(){
        return G;
    }

    public static void main(String[] args){
        String fileName = args[0];
        String separator = args[1];
        SymbolDigraph symbolGraphs = new SymbolDigraph(fileName, separator);
        Digraph G = symbolGraphs.G();
        while(StdIn.hasNextLine()){
            String source = StdIn.readLine();
            int sourceIndex = symbolGraphs.index(source);
            for(int w: G.adj(sourceIndex)){
                StdOut.print(symbolGraphs.name(w)+" ");
            }
            System.out.println();
        }
    }
}
