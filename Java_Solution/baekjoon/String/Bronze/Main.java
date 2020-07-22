import java.io.*;
import java.util.*;

public class Main {
    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        int[] alpha = new int[26];
        Arrays.fill(alpha, -1);

        for(int i=0; i<input.length(); i++) {
            char chr = input.charAt(i);
            int idx = chr - 'a';

            if(alpha[idx] == -1) {
                alpha[idx] = i;
            }
        }

        for(int i=0; i<26; i++) {
            System.out.print(alpha[i] + " ");
        }
    }
}