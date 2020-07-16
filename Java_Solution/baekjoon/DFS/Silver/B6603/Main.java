// 로또

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] lotto;
    static StringBuilder sb;

    static void DFS(int r, int idx, int[] choices) {

        if(r == 6) {
            for(int i=0; i<6; i++) {
                sb.append(choices[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        if(idx >= N) return;

        choices[r] = lotto[idx];
        DFS(r+1, idx+1, choices);
        choices[r] = 0;
        DFS(r, idx+1, choices);
    }

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            if(N==0) break;

            lotto = new int[N];
            int[] choices = new int[6];

            for(int i=0; i<N; i++) {
                lotto[i] = Integer.parseInt(st.nextToken());
            }

            DFS(0, 0, choices);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}