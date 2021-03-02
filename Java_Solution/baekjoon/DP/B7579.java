import java.util.*;
import java.io.*;

public class B7579 {
    static int N, M, totalCost, minCost;
    static int[] bites, costs;
    static int[][] dp;

    public static void calculateMinCost() {
        for(int i=1; i<N+1; i++) {
            for(int j=0; j<totalCost+1; j++) {

                if(costs[i] > j) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    int bite = bites[i] + dp[i-1][j-costs[i]];
                    dp[i][j] = Math.max(dp[i-1][j], bite);
                }

                if(dp[i][j] >= M) {
                    minCost = Math.min(minCost, j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bites = new int[N+1];
        costs = new int[N+1];
        minCost = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) 
            bites[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }
        
        dp = new int[N+1][totalCost+1];
        calculateMinCost();
        System.out.println(minCost);
    }    
}
