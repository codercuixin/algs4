package sort;

import io.StdIn;
import io.StdOut;

import java.util.Comparator;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/15 13:47
 */
public class Shell {
    private Shell(){

    }

    public static void sort(Comparable[] a){
        int n = a.length;
        int h = 1;
        while (h<n/3){
            h = 3*h +1;
        }
        while (h>=1){
            //下面的两个for循环建立h有序数组。
            //h-sort the array
            for(int i=h; i<n; i++){
                //将a[j]插入到a[j-h], a[j-2h], a[j-3h]
                for(int j=i; j>=h && less(a[j], a[j-h]); j-=h){
                    exch(a, j, j-h);
                }
            }
            assert isHSorted(a,h);
            h = h/3;
        }
        assert isSorted(a);
    }

    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static boolean less(Comparator comparator, Object v, Object w){
        return comparator.compare(v, w) < 0;
    }

    private static boolean isHSorted(Comparable[] a, int h){
        for(int i=h; i<a.length;i++){
            if(less(a[i], a[i-h])){
                return false;
            }
        }
        return true;
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
