package sort;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/18 17:32
 */
public class MergeBU {
    private MergeBU(){}

    public static void sort(Comparable[] a){
        int n = a.length;
        Comparable[] aux = new Comparable[n];
        for(int len=1; len<n; len *=2){
            for(int lo=0; lo<n-len; lo+=len+len){
                int mid = lo+len -1;
                int hi = Math.min(lo+2*len-1, n-1);
                merge(a, aux, lo, mid, hi);
            }
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        //copy to aux[]
        for(int k=lo; k<=hi; k++){
            aux[k] = a[k];
        }

        //merge back to a[]
        int i= lo, j = mid+1;
        for(int k=lo; k<=hi;k++){
            //左边子数组已经合并完
            if(i>mid){
                a[k] = aux[j++];
            }
            //右边子数组已经合并完
            else if(j>hi){
                a[k] = aux[i++];
            }
            //右边子数组当前值小于左边子数组的当前值
            else if(less(aux[j], aux[i])){
                a[k] = aux[j++];
            }else{
                //右边子数组当前值大于等于左边子数组的当前值
                a[k] = aux[i++];
            }
        }
        assert isSorted(a, lo, hi);
    }

    /***********************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }


    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
//  ***************************************************************************/


    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
    /**
     * Reads in a sequence of strings from standard input; bottom-up
     * mergesorts them; and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        MergeBU.sort(a);
        show(a);
    }
}
