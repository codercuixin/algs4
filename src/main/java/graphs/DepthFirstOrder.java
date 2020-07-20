package graphs;

import fundementals.Queue;
import fundementals.Stack;
import io.In;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/13 13:58
 */
public class DepthFirstOrder {
    //marked[v] = is there an s-v path?
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G){
        marked = new boolean[G.V()];
        pre= new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        for(int v=0; v<G.V(); v++){
            if(!marked[v]) {
                dfs(G, v);
            }
        }
    }

    //depth first search form v
    private void dfs(Digraph G, int v){
        marked[v] = true;
        pre.enqueue(v);
        for(int w: G.adj(v)){
            if(!marked[w]){
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }
   public Iterable<Integer> pre(){
        return pre;
   }
   public Iterable<Integer> post(){
        return post;
   }
   public Iterable<Integer> reversePost(){
        return reversePost;
   }


    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DepthFirstOrder dfs = new DepthFirstOrder(G);
        System.out.print("pre: ");
        for(int i: dfs.pre()){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.print("post: ");
        for(int i: dfs.post()){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.print("reversePost: ");
        for(int i: dfs.reversePost()){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
