import java.util.*;
import java.io.*;

public class B11401_euclidean {
    static final int MOD = 1000000007;
    static final int MAX = 4000000;
    static long[] factorial;
    static int N, K;

    public static void getFactorial() {
        factorial = new long[MAX+1];
        factorial[0] = 1;
        factorial[1] = 1;
        for(int i=2; i<MAX+1; i++) {
            factorial[i] = factorial[i-1] * i % MOD;
        }
    }

    public static long euclidean_Bezout_identity(long p, long B) {
        long s = 0;
        long s0 = 1;
        long s1 = 0;
        long t = 1;
        long t0 = 0;
        long t1 = 1;
        long tempB = B;

        while(true) {
            if(p%B > 0) {
                s = s0 - s1*(p/B);
                s0 = s1;
                s1 = s;
                t = t0 - t1*(p/B);
                t0 = t1;
                t1 = t;

                tempB = B;
                B = p%B;
                p =tempB;

            } else {
                break;
            }
        }

        return t;
    }
    

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        getFactorial();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // euclidean Theorem
        // ps + Bt = 1
        long B = factorial[K] * factorial[N-K] % MOD;
        long t = euclidean_Bezout_identity(MOD, B);
        long result = factorial[N] * t % MOD;
        if(result < 0) result += MOD;

        System.out.println(result);
    }
}   
