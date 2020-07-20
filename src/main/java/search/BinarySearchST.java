package search;


import fundementals.Queue;

import java.util.Arrays;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/28 11:13
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;

    public BinarySearchST(int capacity){
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size(){
        return N;
    }
    public boolean isEmpty(){
        return size() == 0;
    }

    public Value get(Key key){
        if(isEmpty()){
            return null;
        }
        int i = rank(key);
        if(i<N && keys[i].compareTo(key) == 0){
            return vals[i];
        }else {
            return null;
        }
    }
    public int rank(Key key){
        int lo = 0, hi = N -1;
        while (lo <= hi){
            int mid = lo + (hi-lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if(cmp < 0){
                hi = mid - 1;
            }else if(cmp > 0){
                lo = mid +1;
            }else{
                return mid;
            }
        }
        return lo;
    }

    public void put(Key key, Value val){
        //查找键，若找到则更新键，否则创建新的元素
        int i = rank(key);
        if(i<N && keys[i].compareTo(key) == 0){
            vals[i] = val;
            return;
        }
        //将较大的键后移一格
        for(int j=N; j>i;j--){
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }


    public Key min(){
        return keys[0];
    }
    public Key max(){
        return keys[N-1];
    }
    public Key select(int k){
        return keys[k];
    }
    public Key ceiling(Key key){
        int i = rank(key);
        return keys[i];
    }
    public Key floor(Key key){
        //todo 练习 3.1.17
        int i = rank(key);

        return null;
    }
    public Key delete(Key key){
        //todo 练习 3.1.17
        int i = rank(key);

        return null;
    }
    public boolean contains(Key key){
        if(get(key) != null){
            return true;
        }else{
            return  false;
        }
    }
    public Iterable<Key> keys()
    {
        return Arrays.asList(keys);
    }
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        for(int i=rank(lo);i<rank(hi); i++){
            q.enqueue(keys[i]);
        }
        if(contains(hi)){
            q.enqueue(keys[rank(hi)]);
        }
        return q;
    }
}
