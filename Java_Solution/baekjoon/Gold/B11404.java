import java.io.*;
import java.util.*;

public class B11404 {
    static final int MAX = 100000000;
    static int N, M;
    static int[][] minPrices;

    public static void initMinPrices() {
        for(int i=1; i<N+1; i++) {
            Arrays.fill(minPrices[i], MAX);
        }
    }

    public static void calculateMinPrices() {
        for(int k=1; k<N+1; k++) {
            for(int s=1; s<N+1; s++) {
                for(int e=1; e<N+1; e++) {

                    minPrices[s][e] = Math.min(minPrices[s][e],
                                minPrices[s][k] + minPrices[k][e]);
                }
            }
        }
    }
    
    public static void printArray(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                if(arr[i][j] == MAX)
                     arr[i][j] = 0;
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        minPrices = new int[N+1][N+1];
        initMinPrices();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            minPrices[s][e] = Math.min(minPrices[s][e], p);
        }
        
        calculateMinPrices();
        printArray(minPrices);
    }
}
