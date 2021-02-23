import java.io.*;
import java.util.*;

public class B20040 {
    static int N, M;
    static int[] cycleGame;

    public static boolean union(int idx1, int idx2) {
        idx1 = find(idx1);
        idx2 = find(idx2);

        // 트리의 루트노드 변경
        if(idx1 == idx2) return true;
        else {
            cycleGame[idx2] = idx1;
            return false;
        }
    }

    public static int find(int idx) {
        if(cycleGame[idx] == idx) 
            return idx;
        else
            return cycleGame[idx] = find(cycleGame[idx]);
    }

    public static void setCycleGameArr() {
        cycleGame = new int[N];
        for(int i=0; i<N; i++) cycleGame[i] = i;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int result = 0;
        setCycleGameArr();

        for(int i=1; i<M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int idx1 = Integer.parseInt(st.nextToken());
            int idx2 = Integer.parseInt(st.nextToken());

            if(union(idx1, idx2)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}