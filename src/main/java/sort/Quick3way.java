package sort;

import io.StdIn;
import io.StdOut;
import io.StdRandom;

import java.util.Comparator;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/18 20:15
 */
public class Quick3way {
    private Quick3way(){}

    private static final int M = 2;

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi){
//        if(hi <=lo){
//            return;
//        }
        if(hi<=lo+M){
            Insertion.sort(a, lo, hi);
            return;
        }
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo +1;
        while(i<=gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exch(a, lt++, i++);
            }
            else if(cmp >0){
                exch(a, gt--,i);
            }
            else{
                i++;
            }
        }
        sort(a, lo ,lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo ,hi);
    }



    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static boolean less(Comparator comparator, Object v, Object w){
        return comparator.compare(v, w) < 0;
    }

    //exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    //is the array a[] soted, [lo, hi]
    private static boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i=lo+1; i<= hi;i++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }
    private static boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length-1);
    }
    private static boolean isSorted(Object[] a, Comparator comparator, int lo, int hi){
        for(int i=lo+1; i<=hi; i++){
            if(less(comparator, a[i], a[i-1])){
                return  false;
            }
        }
        return true;
    }
    private static boolean isSorted(Object[] a, Comparator comparator){
        return isSorted(a, comparator, 0, a.length-1);
    }

    //print array to standard output
    private static void show(Comparable[] a){
        for(int i=0; i<a.length; i++){
            StdOut.println(a[i]);
        }
    }
    /**
     * Reads in a sequence of strings from standard input; quicksorts them;
     * and prints them to standard output in ascending order.
     * Shuffles the array and then prints the strings again to
     * standard output, but this time, using the select method.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);
        assert isSorted(a);

//        // shuffle
//        StdRandom.shuffle(a);
//
//        // display results again using select
//        StdOut.println();
//        for (int i = 0; i < a.length; i++) {
//            String ith = (String) Quick.select(a, i);
//            StdOut.println(ith);
//        }
    }
}
