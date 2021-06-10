import java.util.*;
import java.io.*;

public class KMP {
    public static void main(String[] args) throws IOException {
        String parent = "abababacabaababacaca";
        String pattern = "ababaca";
        // String parent = "ababacabacaabacaaba";
        // String pattern = "abacaaba";
        startKMP(parent, pattern);
    }

    public static int[] makePatternTable(String str) {
        int strSize = str.length();
        int j = 0;
        int[] table = new int[strSize];
        
        for(int i=1; i<strSize; i++) {
            while(j > 0 && str.charAt(j) != str.charAt(i)) {
                j = table[j-1];
            }
            
            if(str.charAt(j) == str.charAt(i)) {
                table[i] = j+1;
                j += 1;
            }
        }

        return table;
    }

    public static void startKMP(String parent, String pattern) {
        System.out.println(parent);
        System.out.println(pattern);

        int[] table = makePatternTable(pattern);
        System.out.println(Arrays.toString(table) + "\n");

        int parentSize = parent.length();   
        int patternSize = pattern.length();
        int j = 0;

        for(int i=0; i<parentSize; i++) {
            while(j>0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j-1];
            }

            if(parent.charAt(i) == pattern.charAt(j)) {
                if(j == patternSize - 1) {
                    System.out.println((i-patternSize+1) + "번째 index에서 동일한 문자열 발견");
                    j = table[j];
                } else {
                    j += 1;
                }
            }
        }
    }
}
