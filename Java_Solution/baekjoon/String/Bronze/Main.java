import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int[] alpha = new int[26];
        int idx = 0;
        int maxCnt = 0;
        int maxIdx = -1;
        char res = '?';
        
        for(int i=0; i<input.length(); i++) {
            char chr = input.charAt(i);

            if((int)chr >= 97) {
                idx = chr - 'a';
            } else {
                idx = chr - 'A';
            }

            alpha[idx] += 1;
            maxCnt = Math.max(maxCnt, alpha[idx]);
        }

        for(int i=0; i<26; i++) {
            if(maxCnt == alpha[i]) {
                if(maxIdx == -1) {
                    maxIdx = i;
                    res = (char)(i + 65);
                } else {
                    res = '?';
                    break;
                }
            }
        }

        System.out.println(res);
    }
}