import java.util.*;
import java.io.*;

public class B2749_피사노주기 {
    static final int MOD = 1000000;
    static final int CYCLE = 15 * MOD/10;
    static long N;
    static long[] fiboArr;

    public static void setFibo() {
        // 피사노 주기, if MOD == 10^(k) k>2
        // 주기는 15*10^(k-1)
        fiboArr = new long[CYCLE+1];
        fiboArr[0] = 0;
        fiboArr[1] = 1;
        for(int i=2; i<CYCLE+1; i++) {
            fiboArr[i] = (fiboArr[i-1] + fiboArr[i-2])%MOD;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        setFibo();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Long.parseLong(st.nextToken());
        N %= CYCLE;

        System.out.println(fiboArr[(int)N]);
    }
}   
