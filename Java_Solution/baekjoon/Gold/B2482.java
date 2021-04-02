import java.io.*;
import java.util.*;

public class B2482 {
    static final int MOD = 1000000003;
    static int N, K, totalCount;
    static int[][] dp;

    public static int choiceNotNearColor(int n, int k) {
     
        if(n < 0 || k < 0) 
            return 0;
        
        if(dp[n][k] != -1) {
            return dp[n][k];
        } else if(k == 1) {
            return dp[n][k] = n;
        } else if(k == 0) {
            return dp[n][k] = 1;
        }

        // 이전에 선택한 색상도 전체 갯수에 포함해야 하므로 n+1
        if(k*2 > n+1) {
            dp[n][k] = 0;
        } else {
            // 처음과 달리 선택한 색상과 바로 옆의 색상은 선택할 수 없으므로 (n-2, k-1)
            dp[n][k] = (choiceNotNearColor(n-2, k-1) + choiceNotNearColor(n-1, k))%MOD;
        }

        return dp[n][k];
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        dp = new int[N+1][K+1];
        for(int i=0; i<N+1; i++) {
            Arrays.fill(dp[i], -1);
        }

        if(N==1 && K==1) {
            totalCount = 1;
        } else {
            // 1을 뽑은 경우(N-3, K-1) + 1을 뽑지 않은 경우(N-1, K)
            totalCount = (choiceNotNearColor(N-3, K-1) + choiceNotNearColor(N-1, K))%MOD;
        }

        System.out.println(totalCount);
    }
}
