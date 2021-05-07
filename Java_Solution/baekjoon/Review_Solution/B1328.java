import java.util.*;
import java.io.*;

public class B1328 {

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long MODE = 1000000007;

        long[][][] dp = new long[N+1][N+1][N+1];
        dp[1][1][1] = 1;

        for(int n=2; n<=N; n++) {
            for(int left=1; left<=n; left++) {
                for(int right=1; right<=n; right++) {
                    dp[n][left][right] += dp[n-1][left-1][right];
                    dp[n][left][right] += dp[n-1][left][right-1];
                    dp[n][left][right] += dp[n-1][left][right]*(n-2);
                    dp[n][left][right] %= MODE;
                }
            }
        }

        System.out.println(dp[N][L][R]);
    }
}   