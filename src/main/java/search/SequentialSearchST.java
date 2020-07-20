package search;

import java.util.LinkedList;
import java.util.List;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/28 10:07
 */
public class SequentialSearchST<Key, Value> {
    /**
     * 链表头节点
     */
    private Node first;
    private class Node{
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next){
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key){
        for(Node x = first; x!= null; x=x.next){
            if(x.key.equals(key)){
                return x.val;
            }
        }
        return null;
    }

    public void put(Key key, Value value){
        for(Node x=first; x!=null; x=x.next){
            if(x.key.equals(key)){
                //更新
                x.val = value;
                return;
            }
        }
        first = new Node(key, value, first);
    }

    public int size(){
        int size = 0;
        for(Node x=first; x!=null; x=x.next){
            size ++;
        }
        return size;
    }

    public Iterable<Key> keys(){
        List<Key> keys = new LinkedList<Key>();
        for(Node x=first; x!=null; x=x.next){
           keys.add(x.key);
        }
        return keys;
    }
    public void delete(Key key){
        if(size() == 0){
            return;
        }
        //如果是首节点
        if(first.key.equals(key)){
            first = first.next;
            return;
        }
        //如果不是首节点
        Node before = first;
        for(Node x=first.next; x!=null; before=x, x=x.next){
            if(x.key.equals(key)){
                before.next = x.next;
                return;
            }
        }
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }


}
