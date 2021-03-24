import java.io.*;
import java.util.*;

public class B2470 {
    static final int MAX = 1000000000;
    static int N;
    static int sol1, sol2;
    static int[] solutions;

    public static void mixTwoSolution() {
        int s = 0;
        int e = N-1;
        int minResult = 2*MAX + 1;
        int result = 0;

        while(s < e) {
            result = solutions[s] + solutions[e];

            if(Math.abs(minResult) > Math.abs(result)) {
                sol1 = solutions[s];
                sol2 = solutions[e];
                minResult = sol1 + sol2;
            }

            if(result > 0) {
                e -= 1;
            } else if(result < 0) {
                s += 1;
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        solutions = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);

        mixTwoSolution();
        System.out.println(sol1+ " " + sol2);
    }
}
