package search;

import io.StdIn;
import io.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/28 10:32
 */
public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private TreeMap<Key,Value> st;

    /**
     * Initializes an empty symbol table;
     */
    public ST(){
        st = new TreeMap<Key, Value>();
    }


    public Value get(Key key){
        if(key == null){
            throw new IllegalArgumentException("calls get() with null key");
        }
        return st.get(key);
    }

    public void put(Key key, Value val){
        if(key == null){
            throw new IllegalArgumentException("calls put() with null key");
        }
        if(val == null){
            st.remove(key);
        }
        else{
            st.put(key, val);
        }
    }

    public void delete(Key key){
        if(key == null){
            throw new IllegalArgumentException("calls put() with null key");
        }
        st.remove(key);
    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException("calls put() with null key");
        }
        return st.containsKey(key);
    }

    public int size(){
        return st.size();
    }

    public boolean isEmpty(){
        return size() == 0;
    }
    public Iterable<Key> keys() {
        return st.keySet();
    }

    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }

    public Key min(){
        if(isEmpty()){
            throw new IllegalArgumentException("calls min() with empty symbol table");
        }
        return st.firstKey();
    }
    public Key max(){
        if(isEmpty()){
            throw new IllegalArgumentException("calls max() with empty symbol table");
        }
        return st.lastKey();
    }

    public Key ceiling(Key key){
        if(key == null){
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        Key k = st.ceilingKey(key);
        if(k == null){
            throw new NoSuchElementException("argument to ceiling() is too large");
        }
        return k;
    }
    public Key floor(Key key){
        if(key == null){
            throw new IllegalArgumentException("argument to floor() is null");
        }
        Key k = st.floorKey(key);
        if(k == null){
            throw new NoSuchElementException("argument to ceiling() is too small");
        }
        return k;
    }

    public static void main(String[] args){
        ST<String, Integer> st = new ST<String,Integer>();
        for(int i=0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for(String s: st.keys()){
            StdOut.println(s+" "+st.get(s));
        }
    }

}
