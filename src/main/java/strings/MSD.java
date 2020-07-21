package strings;

import io.In;
import io.StdIn;
import io.StdOut;
import sort.Insertion;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/21 17:05
 */
public class MSD {
    private static int R = 256; //基数
    private static final int M = 0; //小数组的切换阈值，小于该阈值改用插入排序
    private static String[] aux; //数组分类的辅助数据
    private static int charAt(String s, int d){
        if(d < s.length()){
            return s.charAt(d);
        }else{
            return -1;
        }
    }

    public static void sort(String[] a){
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }
    private static void sort(String[] a, int lo, int hi, int d){
        //以第d个字符为键将a[lo]至a[hi]排序
        if(hi <= lo+M){
            //todo fix it
//            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R+2];//计算频率
        for(int i=lo; i<=hi; i++){
            count[charAt(a[i], d) + 2] ++;
        }
        for(int r=0; r<R+1; r++){ //将频率转换为索引
            count[r+1] += count[r];
        }
        for(int i=lo; i<=hi; i++){ //数据分类
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        for(int i=lo; i<=hi; i++){//回写
            a[i] = aux[i-lo];
        }
        //递归的以每个字符为键进行排序
        for(int r=0; r<R; r++){
            sort(a, lo+count[r], lo+count[r+1]-1, d+1);
        }
    }

    public static void main(String[] args) {
        String[] a = new In("shells.txt").readAllStrings();
        int n = a.length;
        sort(a);
        for (int i = 0; i < n; i++)
            StdOut.println(a[i]);
    }
}
