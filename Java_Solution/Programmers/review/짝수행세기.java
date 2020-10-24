
class 짝수행세기 {
    static int N, M;
    static final int DIVIDE = 10000019;
    static int[] col_OneCnt;
    static long[][] dp, nCr;
    
    public static void combination() {
        nCr[0][0] = 1;
        
        for(int i=1; i<N+1; i++) {
            nCr[i][0] = 1;
            for(int j=1; j<N+1; j++) {
                nCr[i][j] = nCr[i-1][j-1] + nCr[i-1][j];
                nCr[i][j] %= DIVIDE;
            }
        }
    }
    
    public static void countOne(int[][] a) {
        for(int i=0; i<M; i++) {
            for(int j=0; j<N; j++) {
                col_OneCnt[i] += a[j][i];
            }
        }
    }
    
    public static void calculateDpCase() {
        for(int col=1; col<M; col++) {
            int oneCnt = col_OneCnt[col];
            
            for(int evenNum=0; evenNum<=N; evenNum++) {
                if(dp[col][evenNum] == 0) continue;
                
                for(int insertOneCnt=0; insertOneCnt<=oneCnt; insertOneCnt++) {
                    if(evenNum < insertOneCnt) continue;
                    
                    int curEvenNum = evenNum + oneCnt - 2*insertOneCnt;
                    
                    if(curEvenNum > N) continue;
                    
                    long result = nCr[evenNum][insertOneCnt] * nCr[N - evenNum][oneCnt - insertOneCnt] % DIVIDE;
                    dp[col+1][curEvenNum] += (result * dp[col][evenNum]);
                    dp[col+1][curEvenNum] %= DIVIDE;
                }
            }
        }
    }
    
    public static long solution(int[][] a) {
        N = a.length;
        M = a[0].length;
        col_OneCnt = new int[M+1];
        nCr = new long[N+1][N+1];
        dp = new long[M+1][N+1];

        combination();
        countOne(a);
        dp[1][N-col_OneCnt[0]] = nCr[N][N-col_OneCnt[0]];
        
        calculateDpCase();
        
        return dp[M][N];
    }
    
    public static void main(String[] args) {
        int[][] a = {};
        solution(a);
    }
}