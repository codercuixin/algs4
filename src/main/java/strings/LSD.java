package strings;

import io.In;
import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/21 16:11
 */
public class LSD {
    public static void sort(String[] a, int W){
        //通过前W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for(int d= W-1; d>=0; d--){
            //根据第d个字符用键索引计数法排序
            int[] count = new int[R+1]; //计算出现频率
            for(int i=0; i<N; i++){
                count[a[i].charAt(d) + 1]++;
            }
            for(int r=0; r<R; r++){ //将频率转换成索引
                count[r+1] += count[r];
            }
            for(int i=0; i<N; i++){ //将元素分类
                aux[count[a[i].charAt(d)]++] = a[i];
            }
            for(int i=0; i<N; i++){//回写
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = new In("words.txt").readAllStrings();
        int n = a.length;

        // check that strings have fixed length
        int w = a[0].length();
        for (int i = 0; i < n; i++)
            assert a[i].length() == w : "Strings must have fixed length";

        // sort the strings
        sort(a, w);

        // print results
        for (int i = 0; i < n; i++)
            StdOut.println(a[i]);
    }
}
