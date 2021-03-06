package sort;

import io.StdIn;
import io.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/19 9:59
 */
public class MaxPQ<Key> implements Iterable<Key> {
    //store items at indicies 1 to n
    private Key[] pq;
    //number of items on priority queue
    private int n;
    //optional comparator
    private Comparator<Key> comparator;

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    //Initializes an empty priority queue
    public MaxPQ() {
        this(1);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity
     *
     * @param initCapacity 优先级队列的初始容量
     * @param comparator   the order in which to compare the keys
     */
    public MaxPQ(int initCapacity, Comparator<Key> comparator) {
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }


    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    public Key max(){
        if(isEmpty()){
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }
    // helper function to double the size of the heap array
    private void resize(int capacity){
        assert capacity > n;
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * Adds a new key to this priority queue
     */
    public void insert(Key x){
        //double size of array if necessary
        if(n == pq.length - 1){
            resize( 2 * pq.length);
        }
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    /**
     * Removes and returns a largest key on priority queue.
     * @return a largest key on this priority queue
     */
    public Key delMax(){
        if(isEmpty()){
            throw new NoSuchElementException("Priority queue underflow");
        }
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        //to avoid loitering and help with garbage collection
        pq[n+1] = null;
        //if necessary, resize to small pq array
        if(n>0 && ( n == (pq.length-1)/ 4)){
            resize(pq.length/2);
        }
        return max;
    }

    /**************************************************************
     * Helper functions to restore the heap invariant
     **************************************************************/
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j + 1 <= n && less(j, j + 1)) {
                j++;
            }
            if (!less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    /**************************************************************
     * Helper functions for compares and swaps
     **************************************************************/
    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n[ a max heap?
    private boolean isMaxHeap() {
        //检查数据有效性
        for (int i = 1; i < n; i++) {
            if (pq[i] == null) {
                return false;
            }
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) {
                return false;
            }
        }
        if (pq[0] != null) {
            return false;
        }
        return isMaxHeapOrdered(1);
    }
    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > n) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(k, left)) {
            return false;
        }
        if (right <= n && less(k, right)) {
            return false;
        }
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }
    /**************************************************************
     * Iterator
     **************************************************************/
    public Iterator<Key> iterator() {
            return new HeapIterator();

    }
    private class HeapIterator implements  Iterator<Key>{
        //create a new pq
        private MaxPQ<Key> copy;

        /**
         * add all items to copy of heap
         * take linear time since already in heap order so keys move
         */
        public HeapIterator(){
            if(comparator == null){
                copy = new MaxPQ<Key>(size());
            }else{
                copy = new MaxPQ<Key>(size(), comparator);
            }
            for(int i=1; i<=n;i++){
                copy.insert(pq[i]);
            }
        }
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public Key next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return copy.delMax();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * Unit tests the {@code MaxPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }


}
