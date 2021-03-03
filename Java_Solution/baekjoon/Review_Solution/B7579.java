import java.util.*;
import java.io.*;

public class B7579 {
    static int N, M, totalCost, minCost;
    static int[] memories, costs;
    static int[][] costDP;

    public static void findMinCost() {
        for(int i=1; i<N+1; i++) {
            for(int j=0; j<totalCost+1; j++) {
                if(costs[i] > j) {
                    costDP[i][j] = costDP[i-1][j];
                } else {
                    int cost = memories[i] + costDP[i-1][j-costs[i]];
                    costDP[i][j] = Math.max(costDP[i-1][j], cost);
                }

                if(costDP[i][j] >= M) {
                    minCost = Math.min(minCost, j);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memories = new int[N+1];
        costs = new int[N+1];
        minCost = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            memories[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<N+1; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            totalCost += costs[i];
        }

        costDP = new int[N+1][totalCost+1];
        findMinCost();
        System.out.println(minCost);
    }
}   
