import java.util.*;
import java.io.*;

public class B2482 {
    static final int MOD = 1000000003;
    static int N, K;
    static int[][] dp;

    public static int findTotalCase(int n, int k) {

        if(n<0 || k<0) 
            return 0;

        if(dp[n][k] != -1) {
            return dp[n][k];
        } else if(k == 1) {
            return dp[n][k] = n;
        } else if(k == 0) {
            return dp[n][k] = 1;
        }

        if(2*k > n+1) {
            return dp[n][k] = 0;
        } else {
            dp[n][k] = (findTotalCase(n-2, k-1) + findTotalCase(n-1, k))%MOD;
        }

        return dp[n][k];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int totalCase = 1;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        dp = new int[N+1][K+1];
        for(int i=0; i<N+1; i++) {
            Arrays.fill(dp[i], -1);
        }
    
        if(N != 1 || K != 1) {
            // 1번을 선택한 경우 + 1번을 선택하지 않은 경우
            totalCase = (findTotalCase(N-3, K-1) + findTotalCase(N-1, K))%MOD;
        }
        
        System.out.println(totalCase);
    }
}   
