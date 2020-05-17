import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int coinArr[] = new int[N];
        int minCnt = 0;

        for(int i=0; i<N; i++) {
            coinArr[i] = Integer.parseInt(br.readLine());
        }

        for(int i=N-1; i>=0; i--) {
            int temp = K/coinArr[i];
            if(temp > 0) {
                minCnt += temp;
                K -= coinArr[i] * temp; 
            }
        }

        System.out.println(minCnt);
    }
}