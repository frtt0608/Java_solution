import java.io.*;
import java.util.*;

public class B1328 {
    static final int MODE = 1000000007;
    static int N, L, R;
    static long[][][] dp;

    public static void getTotalCase() {
        dp[1][1][1] = 1;

        for(int n=2; n<N+1; n++) {
            for(int left=1; left<=L; left++) {
                for(int right=1; right<=R; right++) {
                    dp[n][left][right] += dp[n-1][left][right] * (n-2);
                    dp[n][left][right] += dp[n-1][left-1][right];
                    dp[n][left][right] += dp[n-1][left][right-1];
                    dp[n][left][right] %= MODE;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        dp = new long[N+1][L+1][R+1];

        getTotalCase();
        System.out.println(dp[N][L][R]);
    }
}
