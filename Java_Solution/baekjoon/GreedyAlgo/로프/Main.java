import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int lope[] = new int[N];
        int maxWeight = 0;
        int idx = 0;

        for(int i=0; i<N; i++) {
            lope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(lope);

        while(true) {
            if(maxWeight < lope[idx]*(N-idx)) {
                maxWeight = lope[idx]*(N-idx);
            }
            idx++;
            if(idx==N) break;
        }

        System.out.println(maxWeight);
    }
}