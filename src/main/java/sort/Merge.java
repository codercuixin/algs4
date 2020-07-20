package sort;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/18 16:36
 */
public class Merge {
    private Merge(){}

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

    //mergesort a[lo...hi] using auxiliary array aux[lo..hi]
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        if(hi<=lo){
            return;
        }
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    /**
     * sort the array in the ascending order, using the natural orfer
     * @param a the array to sorted
     */
    public static void sort(Comparable[] a){
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }


    /***************************************************************************
     *  Helper sorting function.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }
    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = {"h", "e", "l", "l", "o"};
        Merge.sort(a);
        show(a);
    }
}
