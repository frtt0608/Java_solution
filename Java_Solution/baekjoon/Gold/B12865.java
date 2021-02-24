import java.io.*;
import java.util.*;

public class B12865 {
    static int N, K;
    static int[][] item;
    static int[][] knapsack;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        item = new int[N+1][2];
        knapsack = new int[N+1][K+1];

        for(int i=1; i<N+1; i++) {
            st = new StringTokenizer(br.readLine());
            item[i][0] = Integer.parseInt(st.nextToken());
            item[i][1] = Integer.parseInt(st.nextToken());
        }

        // i는 item번호, j는 무게
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<K+1; j++) {
                // 제한된 무게 j보다 item이 무거울 경우, 이전 행의 값을
                // item을 넣을 수 있는 경우, 
                // 이전 행의 값과 (item의 가치 + 이전 행에서 item을 넣을 수 있는 공간을 마련한 열의 값)을 비교
                if(item[i][0] > j) {
                    knapsack[i][j] = knapsack[i-1][j];
                } else {
                    knapsack[i][j] = Math.max(knapsack[i-1][j], 
                                            item[i][1] + knapsack[i-1][j-item[i][0]]);
                }
            }
        }

        System.out.println(knapsack[N][K]);
    }
}