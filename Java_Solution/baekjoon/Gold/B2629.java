import java.io.*;
import java.util.*;

public class B2629 {
    static int N, M, total;
    static int[] weight;
    static boolean[][] dp;

    public static void knaksack(int idx, int sum) {
        if(dp[idx][sum]) return;
        dp[idx][sum] = true;
        if(idx >= N) return;

        knaksack(idx+1, sum+weight[idx]);
        knaksack(idx+1, Math.abs(sum-weight[idx]));
        knaksack(idx+1, sum);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            total += weight[i];
        }
        dp = new boolean[N+1][total+1];
        knaksack(0, 0);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int check = Integer.parseInt(st.nextToken());
            if(check <= total && dp[N][check]) sb.append("Y ");
            else sb.append("N ");
        }

        System.out.println(sb.toString());
    }
}