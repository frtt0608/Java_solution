import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        long finalNum = 0;
        long numlen = 1;
        long nine = 9;

        while(K > numlen * nine) {
            finalNum += nine;
            K -= numlen * nine;
            numlen += 1;
            nine *= 10;
        }
        
        finalNum += (K-1)/numlen + 1;
        if(finalNum > N) System.out.println(-1);
        else {
            int idx = (int)((K-1)%numlen);
            System.out.println(Long.toString(finalNum).charAt(idx));
        }
    }
}