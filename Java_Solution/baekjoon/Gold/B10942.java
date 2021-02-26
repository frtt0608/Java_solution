import java.io.*;
import java.util.*;

public class B10942 {
    static int N;
    static int[] arr;
    static int[][] dp;

    public static void isPalindrome() {
        for(int i=0; i<N; i++) {
            for(int s=0; s<N-i; s++) {
                int e = s+i;

                if(s == e) {
                    dp[s][e] = 1;
                    continue;
                }

                if(e-s == 1) {
                    if(arr[s] == arr[e]) dp[s][e] =  1;
                    else dp[s][e] = 0;

                    continue;
                }

                if(arr[s] == arr[e] && dp[s+1][e-1] == 1)
                    dp[s][e] = 1;
                else dp[s][e] = 0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[N+1][N+1];
        String[] input = br.readLine().split(" ");

        for(int i=0; i<N; i++) { 
            arr[i] = Integer.parseInt(input[i]); 
        }
        isPalindrome();

        int M = Integer.parseInt(br.readLine());
        while(M > 0) {
            M -= 1;
            st = new StringTokenizer(br.readLine());

            int S = Integer.parseInt(st.nextToken())-1;
            int E = Integer.parseInt(st.nextToken())-1;

            sb.append(dp[S][E]+"\n");
        }

        System.out.println(sb.toString());
    }
}