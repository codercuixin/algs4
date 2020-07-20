package search;

import io.StdIn;
import io.StdOut;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/28 10:46
 */
public class FrequencyCounter {
    // Do not instantiate.
    private FrequencyCounter() { }

    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = Integer.parseInt(args[0]);
        BST<String, Integer> st = new BST<String, Integer>();

        // compute frequency counts
        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (key.length() < minlen) continue;
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            }
            else {
                st.put(key, 1);
                distinct++;
            }
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max))
                max = word;
            System.out.print(word+", ");
        }
        System.out.println();

        System.out.println("min key is "+st.min() + " length "+ st.min().length());
        System.out.println("max key is "+st.max() + " length "+ st.max().length());

        Integer i ;

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println("floor(yourselfthis) "+ st.floor("yourselfthis"));
        StdOut.println("ceiling(yourselfthis) "+ st.ceiling("yourselfthis"));
        StdOut.println("keys(yourselfflung,youthfulness) "+st.keys("yourselfflung","youthfulness"));
    }
}
