import java.io.*;
import java.util.*;

public class Main {

    public static String mixTheWords(String[] words) {
        int len0 = words[0].length();
        int len1 = words[1].length();

        boolean[][] memo = new boolean[len0+1][len1+1];
        memo[0][0] = true;

        for(int i=1; i<=len0; i++) {
            memo[i][0] = words[0].charAt(i-1) == words[2].charAt(i-1) ? memo[i-1][0]:false;
        }

        for(int i=1; i<=len1; i++) {
            memo[0][i] = words[1].charAt(i-1) == words[2].charAt(i-1) ? memo[0][i-1]:false;
        }

        for(int i=1; i<=len0; i++) {
            for(int j=1; j<=len1; j++) {
                char chr1 = words[0].charAt(i-1), chr2 = words[1].charAt(j-1), target = words[2].charAt(i+j-1);

                if(chr1 != target && chr2 != target) memo[i][j] = false;
                else if(chr1 == target && chr2 != target) memo[i][j] = memo[i-1][j];
                else if(chr1 != target && chr2 == target) memo[i][j] = memo[i][j-1];
                else memo[i][j] = memo[i-1][j] | memo[i][j-1];
            }
        }

        return memo[len0][len1] ? "yes" : "no";
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=N; i++) {
            String[] words = br.readLine().split(" ");
            sb.append("Data set " + i + ": ").append(mixTheWords(words) + "\n");
        }

        System.out.println(sb.toString());
    }
}