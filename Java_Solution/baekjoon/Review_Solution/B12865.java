import java.util.*;
import java.io.*;

public class B12865 {
    static int N, K;
    static int[][] item, knapsack;

    public static void findMaxValue() {
        for(int i=1; i<N+1; i++) {
            for(int w=1; w<K+1; w++) {
                if(item[i][0] > w) {
                    knapsack[i][w] = knapsack[i-1][w];
                } else {
                    int value = item[i][1] + knapsack[i-1][w-item[i][0]];
                    knapsack[i][w] = Math.max(knapsack[i-1][w], value);
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
        K = Integer.parseInt(st.nextToken());
        item = new int[N+1][2];
        knapsack = new int[N+1][K+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }

        findMaxValue();
        System.out.println(knapsack[N][K]);
    }
}   
