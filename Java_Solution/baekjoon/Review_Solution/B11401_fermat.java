import java.util.*;
import java.io.*;

public class B11401_fermat {
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

    public static long fermatGetFastPower(long B) {
        long fermatNum = 1;
        long index = MOD-2;

        while(index > 0) {
            if(index%2 == 1) {
                fermatNum = fermatNum * B % MOD;
            }

            B = B * B % MOD;
            index /= 2;
        }

        return fermatNum;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        getFactorial();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // fermat's little Theorem
        // nCk = (AB^-1) = (AB^(p-2))%p
        long B = factorial[K] * factorial[N-K] % MOD;
        B = fermatGetFastPower(B);

        long result = factorial[N] * B % MOD;
        if(result < 0) result += MOD;

        System.out.println(result);
    }
}   
