package sort;

import io.StdIn;
import io.StdOut;

import java.util.Comparator;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/15 10:21
 */
public class Selection {
    private Selection(){

    }

    /**
     * Rearrange the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a){
        int n = a.length;
        for(int i=0; i<n; i++){
            int min = i;
            for(int j =i+1; j<n; j++){
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
            assert isSorted(a, 0, i);
        }
        assert  isSorted(a);
    }
    /**
     * Rearranges the array in ascending order, using a comparator.
     * @param a the array
     * @param comparator the comparator specifying the order
     */
    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i+1; j < n; j++) {
                if (less(comparator, a[j], a[min])) min = j;
            }
            exch(a, i, min);
            assert isSorted(a, comparator, 0, i);
        }
        assert isSorted(a, comparator);
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
     * reads in a sequence of strings from standard input; selection sorts them;
     * and prints them to standard output in ascending order
     */
    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        Selection.sort(a);
        show(a);
    }

}
