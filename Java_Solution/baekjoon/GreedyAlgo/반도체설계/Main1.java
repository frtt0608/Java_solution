import java.io.*;
import java.util.*;

/**
 * Main
 */
public class Main1 {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int target[] = new int[N];
        int connect[] = new int[N];
        int DP_cnt[] = new int[N];
        Arrays.fill(DP_cnt, 1);
        int maxCnt = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        for(int port=0; port<N; port++) {
            connect[port] = target[port];
            for(int compare=0; compare<port; compare++) {
                if(connect[compare] < connect[port]) DP_cnt[port] = Math.max(DP_cnt[compare]+1, DP_cnt[port]);
            }
        }

        for(int i=0; i<N; i++) {
            maxCnt = Math.max(maxCnt, DP_cnt[i]);
        }

        System.out.println(maxCnt);
    }
}