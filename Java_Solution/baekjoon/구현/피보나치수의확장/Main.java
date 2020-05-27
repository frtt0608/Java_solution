import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N;

    static public void main(String args[]) throws IOException {
        
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int type = 1;
        
        if(N<0) {
            N = -N;
            if(N%2 == 0) {
                type = -1;
            }
        } else if(N == 0) {
            type = 0;
        }
        
        Long numArr[] = new Long[N+1];
        numArr[0] = (long)0;
        numArr[1] = (long)1;       
        for(int i=2; i<=N; i++) {
            numArr[i] = (numArr[i-2] + numArr[i-1]) % 1000000000;
        }

        System.out.println(type);
        System.out.println(numArr[N]);
    }
}