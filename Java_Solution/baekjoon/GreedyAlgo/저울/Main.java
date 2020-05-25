import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, weights[];

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input[] = br.readLine().split(" ");
        weights = new int[N];
        
        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(input[i]);
        }
        
        Arrays.sort(weights); // 1 1 2 3 6 7 30
        int measure = 0;

        for(int i=0; i<N; i++) {
            if(weights[i] <= measure+1) {
                measure += weights[i];
            } else {
                break;  
            }
        }
        measure += 1;

        System.out.println(measure);
    }
}