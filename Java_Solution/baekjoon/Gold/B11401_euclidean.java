import java.io.*;
import java.util.*;

// https://onsil-thegreenhouse.github.io/programming/problem/2018/04/02/problem_combination/

public class B11401_euclidean {
    static final long MOD = 1000000007;
    static int N, K;
    static long[] factorial;

    public static void getFactorial() {
        factorial = new long[N+1];
        factorial[0] = 1;
        factorial[1] = 1;

        for(int i=2; i<N+1; i++) {
            factorial[i] = factorial[i-1] * i % MOD;
        }
    }

    // 확장 유클리드 호제법 ps + Bt = 1;
    // K!(N-K)! = B
    public static long euclidean(long p, long B) {
        long s=0;
        long s0=1;
        long s1=0;
        long t=1;
        long t0=0;
        long t1=1;
        long tempB=0;

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
                p = tempB;
                
                
            } else {
                break;
            }
        }

        return t;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        getFactorial();
        long B = factorial[K]*factorial[N-K]%MOD;
        long t = euclidean(MOD, B);

        long result = ((factorial[N]%MOD) * (t%MOD)) % MOD;
        if(result < 0) result += MOD;

        System.out.println(result);
    }
}
