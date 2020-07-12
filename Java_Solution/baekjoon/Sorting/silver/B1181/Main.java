// 단어 정렬

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Set<String> words = new TreeSet<>(
            new Comparator<String>() {
                @Override
                public int compare(String str1, String str2) {
                    if(str1.length() == str2.length())
                        return str1.compareTo(str2);
                    else
                        return str1.length() - str2.length();
                }
            }
        );

        for(int i=0; i<N; i++) {
            words.add(br.readLine());
        }

        Iterator<String> set_iter = words.iterator();
        while(set_iter.hasNext()) {
            System.out.println(set_iter.next());
        }
    }
}