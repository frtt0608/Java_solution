import java.io.*;
import java.util.*;

public class B10830 {
    static final long MOD = 1000;
    static int N;
    static long B;
    static int[][] arr, resultArr;

    public static int[][] complexArr(int[][] arr1, int[][] arr2) {
        int[][] newArr = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int k=0; k<N; k++) {
                    newArr[i][j] += (arr1[i][k] * arr2[k][j]) % MOD;
                    newArr[i][j] %= MOD;
                }
            }
        }

        return newArr;
    }

    public static void fastPowerArr(int[][] arr) {
        long index = B;

        while(index > 0) {
            if(index%2 == 1) {
                resultArr = complexArr(resultArr, arr);
            }

            arr = complexArr(arr, arr);

            index /= 2;
        }
    }

    public static void printArr(int[][] arr) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = new int[N][N];
        resultArr = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i == j) resultArr[i][j] = 1;
            }
        }

        fastPowerArr(arr);
        printArr(resultArr);
    }
}
