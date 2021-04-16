import java.util.*;
import java.io.*;

public class B13458 {
    static int N, B, C;
    static int[] A;
    static long totalManager;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            A[i] -= B;
            totalManager += 1;
            if(A[i] < 0) continue;

            totalManager += A[i]/C;
            if(A[i]%C > 0) {
                totalManager += 1;
            }
        }

        System.out.println(totalManager);
    }
}