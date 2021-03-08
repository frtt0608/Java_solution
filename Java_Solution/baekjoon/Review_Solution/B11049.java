import java.util.*;
import java.io.*;

public class B11049 {
    static int N;
    static int[][] arr, dp;

    public static void getMinCalculateCount() {
        for(int i=1; i<N; i++) {
            for(int j=0; j<N-i; j++) {
                for(int k=0; k<i; k++) {
                    int count = dp[j][j+k] + dp[j+k+1][i+j] +
                            arr[j][0]*arr[j+k][1]*arr[i+j][1];
                    dp[j][i+j] = Math.min(dp[j][i+j], count);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][2];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        getMinCalculateCount();
        System.out.println(dp[0][N-1]);
    }
}   
