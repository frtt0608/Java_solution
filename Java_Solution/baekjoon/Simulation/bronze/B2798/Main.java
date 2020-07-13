import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {
    static int N, M, resNum;
    static int[] cards;

    static void permutation(int r, int num, boolean[] visited) {
        if(r == 3) {
            if(num <= M) {
                if(M - num < M - resNum) {
                    resNum = num;
                }
            }
            
            return;
        }

        for(int i=0; i<N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            permutation(r+1, num+cards[i], visited);
            visited[i] = false;
        }
    }

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());    
        M = Integer.parseInt(st.nextToken());
        cards = new int[N];
        boolean[] visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0, 0, visited);

        System.out.println(resNum);
    }
}