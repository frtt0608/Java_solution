import java.util.Arrays;

class Solution {
    static int N, M;
    static final int DIVIDE = 10000019;
    static int[] col_OneCnt;
    static long[][] nCr, dp;
    
    public static void calculateColumn(int[][] a) {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                col_OneCnt[i] += a[j][i];
            }
        }
    }
    
    public static void combination(int n) {
        nCr = new long[n+1][n+1];
        nCr[0][0] = 1;
        
        for(int i=1; i<=n; i++) {
            nCr[i][0] = 1;
            for(int j=1; j<=n; j++) {
                nCr[i][j] = nCr[i-1][j-1] + nCr[i-1][j];
                nCr[i][j] %= DIVIDE;
            }
        }
    }
    
    public static void memoization() {
        for(int col=1; col<M; col++) {
            int oneCnt = col_OneCnt[col];
            
            for(int evenCnt=0; evenCnt<=N; evenCnt++) {
                if(dp[col][evenCnt] == 0) continue;
                
                for(int k=0; k<=oneCnt; k++) {
                    if(evenCnt < k) continue;
                    int evenRow = evenCnt + oneCnt - (2 * k);
                    
                    if(evenRow > N) continue;
                    
                    long evenCase = (nCr[evenCnt][k] * nCr[N - evenCnt][oneCnt - k]) % DIVIDE;
                    dp[col+1][evenRow] += (dp[col][evenCnt] * evenCase);
                    dp[col+1][evenRow] %= DIVIDE;
                }
            }
        }
    }
    
    public static long solution(int[][] a) {
        N = a.length;
        M = a[0].length;
        col_OneCnt = new int[M+1];
        dp = new long[M+1][N+1];
        
        calculateColumn(a);
        combination(N);
        
        dp[1][N - col_OneCnt[0]] = nCr[N][N - col_OneCnt[0]];
        
        memoization();
        
        return dp[M][N];
    }
    
    public static void main(String[] args) {
        int[][] a = {};
        solution(a);
    }
}