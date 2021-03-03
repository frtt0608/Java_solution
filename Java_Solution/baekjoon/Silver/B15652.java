import java.io.*;
import java.util.*;

public class B15652 {

    static int N, M;
    static StringBuilder sb;

    public static void permutation(int idx, int preNum, int[] result) {
        if(idx == M) {
            for(int num: result) {
                sb.append(num+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=preNum; i<N+1; i++) {
            result[idx] = i;
            permutation(idx+1, i, result);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        permutation(0, 1, new int[M]);
        System.out.println(sb.toString());
    }
}
