import java.io.*;
import java.util.*;

// https://onsil-thegreenhouse.github.io/programming/problem/2018/04/02/problem_combination/

public class B11401_fermat {
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


    // 페르마의 소정리
    // a^(p-1) = 1(mod p)
    // (AB^-1)%p => B^(p-1)%p = 1
    public static long fermatTheorem(long B) {
        long index = MOD-2;
        long fermatNum = 1;

        // 거듭제곱 빨리하기
        // a^6 = (a^3)^2
        // a^7 = (a^3)^2 * a
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
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        getFactorial();
        long B = factorial[K]*factorial[N-K]%MOD;

        long fermatNum = fermatTheorem(B);
        long result = (factorial[N] * fermatNum)%MOD;
        if(result < 0) result += MOD;

        System.out.println(result);
    }
}
