import java.util.*;
import java.io.*;

public class B2629 {
    static int N;
    static int[] weight;
    static boolean[][] isVerifiable;

    public static void findPossibleWeight(int idx, int sumWeight) {
        if(isVerifiable[idx][sumWeight]) return;
        isVerifiable[idx][sumWeight] = true;
        if(idx >= N) return;

        findPossibleWeight(idx+1, sumWeight + weight[idx]);
        findPossibleWeight(idx+1, sumWeight);
        findPossibleWeight(idx+1, Math.abs(sumWeight - weight[idx]));
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        weight = new int[N];
        int size = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
            size += weight[i];
        }
        isVerifiable = new boolean[N+1][size+1];

        findPossibleWeight(0, 0);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int checkWeight = Integer.parseInt(st.nextToken());
    
            if(checkWeight <= size && isVerifiable[N][checkWeight]) {
                sb.append("Y ");
            } else sb.append("N ");
        }

        System.out.println(sb.toString());
    }
}