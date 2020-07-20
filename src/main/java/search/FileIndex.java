package search;

import io.In;
import io.StdIn;
import io.StdOut;

import java.io.File;
import java.io.FileInputStream;

/**
 * * @Author: cuixin
 * * @Date: 2020/7/3 10:43
 */
public class FileIndex {
    private FileIndex(){}
    public static void main(String[] args){
        ST<String, SET<File>> st = new ST<String, SET<File>>();

        //create inverted index of all files
        //word - list of files in which the word has appeared
        StdOut.println("Indexing file");
        for(String fileName: args){
            StdOut.println(" "+fileName);
            File file = new File(fileName);
            In in = new In(file);
            while(!in.isEmpty()){
                String word = in.readString();
                if(!st.contains(word)) st.put(word, new SET<File>());
                st.get(word).add(file);
            }
        }

        while(!StdIn.isEmpty()){
            String query = StdIn.readString();
            if(st.contains(query)){
                for(File file: st.get(query)){
                    StdOut.println(file.getName());
                }
            }
        }
    }
}
