import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main {
    static int N, weights[];
    static LinkedList<Integer> numList;

    static void perm(int idx, int val) {
        if(idx==N) {
            numList.add(val);
        }

        perm(idx+1, val+weights[idx]);
        perm(idx+1, val);
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String input[] = br.readLine().split(" ");
        weights = new int[N];
        int measure = 0;
        numList = new LinkedList<>();
        

        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(input[i]);
        }
        
        Arrays.sort(weights); // 1 1 2 3 6 7 30

        
        for(int i=0; i<N; i++) {
            // System.out.println(measure + ", " + weights[i]);
            if(weights[i] <= measure+1) {
                measure += weights[i];
            } else {
                measure += 1;
                break;
            }
        }

        System.out.println(measure);
    }
}