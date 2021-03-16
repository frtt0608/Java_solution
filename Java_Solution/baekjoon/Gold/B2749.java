import java.io.*;
import java.util.*;

public class B2749 {
    static final int MOD = 1000000;
    static long N, res;
    static long[] arr;

    public static void setFibonacci() {
        arr = new long[1500000];
        arr[0] = 0;
        arr[1] = 1;
        res = 1;

        for(int i=2; i<1500000; i++) {
            arr[i] = (arr[i-1] + arr[i-2])%MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        setFibonacci();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Long.parseLong(st.nextToken()); 
        
        // 피사노 주기
        // 모듈러가 10^k (k>2)인 경우, 주기는 15*10^(k-1)이 된다.
        long p = 1500000;
        N %= p;

        System.out.println(arr[(int) N]);
    }
}
