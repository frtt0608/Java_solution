import java.util.*;
import java.io.*;

public class B10942 {
    static int N;
    static int[] arr;
    static int[][] dp;

    public static boolean isPalindrome(int s, int e) {
        if(s == e) 
            return true;
        else if(s+1 == e) {
            if(arr[s] == arr[e]) 
                return true;
        } else if(arr[s] == arr[e] && dp[s+1][e-1] == 1) 
            return true;

        return false;
    }

    public static void findPalindrome() {
        for(int len=0; len<N; len++) {
            for(int s=0; s<N-len; s++) {
                int e = s+len;
                if(isPalindrome(s, e)) {
                    dp[s][e] = 1;
                } else {
                    dp[s][e] = 0;
                }
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
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        findPalindrome();
        int M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;

            sb.append(dp[s][e]+"\n");
        }

        System.out.println(sb.toString());
    }
}   
