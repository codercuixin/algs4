package sort;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/22 15:53
 */
public class InsertionX {
    private InsertionX(){}


    public static void sort(Comparable[] a){
        int n = a.length;
        //put smallest elements in position to serve as sentinel
        int exchanges = 0;
        for(int i=n-1; i>0; i--){
            if(less(a[i], a[i-1])){
                exch(a, i, i-1);
                exchanges++;
            }
        }
        if(exchanges == 0){
            return;
        }
        for(int i=2; i<n;i++){
            Comparable v = a[i];
            int j = i;
            while (less(v, a[j-1])){
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
        assert isSorted(a);

    }
    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
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

    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        InsertionX.sort(a);
        show(a);
    }

}
