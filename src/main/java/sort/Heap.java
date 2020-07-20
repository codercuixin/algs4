package sort;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/22 14:20
 */
public class Heap {
    private Heap(){
    }

    public static void sort(Comparable[] pq){
        int n = pq.length;
        //heapify phase 第一阶段，构造最大堆，即堆有序阶段。
        for(int k=n/2; k>=1; k--){
            sink(pq, k, n);
        }
        //sortdown phase 第二阶段，在下沉阶段销毁堆，不断删除最大元素（最大元素交换到数组最后）
        int k= n;
        while (k>1){
            exch(pq, 1, k--);
            //下沉满足最大堆
            sink(pq, 1,k);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    /**
     * 下沉，让元素下沉到合适的位置，不断与两个子元素比较交换，直到满足最大堆要求
     * @param pq
     * @param k
     * @param n
     */
    private static void sink(Comparable[]pq, int k, int n){
        while(2 * k <= n){
            int j = 2*k;
            if(j<n && less(pq, j, j+1)){
                j++;
            }
            if(!less(pq, k, j)){
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     ***************************************************************************/
    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args){
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        show(a);
    }

}
