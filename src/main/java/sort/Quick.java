package sort;

import io.StdIn;
import io.StdOut;
import io.StdRandom;

import java.util.Comparator;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/18 19:33
 */
public class Quick {
    private static final int M = 2;
    private Quick(){}

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
        int j = partition(a, lo ,hi);
        sort(a, lo ,j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo ,hi);
    }

    private static int partition(Comparable[] a, int lo ,int hi){
        int i= lo;
        int j= hi+1;
        Comparable v = a[lo];
        while (true){
            //find item on lo to swap
            while (less(a[++i], v)){
                if(i==hi){
                    break;
                }
            }
            //find item on hi to swap
            while (less(v, a[--j])){
                if(j==lo){
                    break;
                }
            }
            //check if pointers cross
            if(i>=j){
                break;
            }
            exch(a, i,j);
        }

        //put partitioning item v at a[j]
        exch(a,lo, j);
        //now, a[lo .. j-1]<=a[j]<=a[j+1 .. hi]
        return j;
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
