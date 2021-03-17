import java.util.*;
import java.io.*;

public class B11444 {
    static final int MOD = 1000000007;
    static long N;
    static long[][] arr = new long[][] {{1, 1}, {1, 0}};
    static long[][] resArr;

    public static long[][] complexArray(long[][] arr1, long[][] arr2) {
        long[][] temp = new long[2][2];

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                for(int k=0; k<2; k++) {
                    temp[i][j] += arr1[i][k] * arr[k][j] % MOD;
                }
            }
        }

        return temp;
    }

    public static void powerArray(long N) {
        resArr = new long[][] {{1,0},{0,1}};

        while(N > 0) {
            if(N%2 == 1) {
                resArr = complexArray(resArr, arr);
            }

            arr = complexArray(arr, arr);
            N /= 2;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Long.parseLong(st.nextToken());
        powerArray(N-2);
        
        long res =(resArr[0][0] + resArr[0][1])%MOD;
        System.out.println(res);
    }
}   
