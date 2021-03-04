import java.io.*;
import java.util.*;

public class B5014 {
    static int F, S, G, U, D, minCount;
    static int[] dp;

    public static void moveToStartLink(int current, int count) {
        if(current > F || current <= 0) return;

        if(current == G) {
            dp[G] = Math.min(dp[G], count);
            return;
        }
        
        if(current+U <= F) {    
            if(dp[current+U] > dp[current]+1) {
                dp[current+U] = dp[current]+1;
                moveToStartLink(current+U, count+1);
            }
        }
        
        if(current-D > 0) {
            if(dp[current-D] > dp[current]+1) {
                dp[current-D] = dp[current]+1;
                moveToStartLink(current-D, count+1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        F = Integer.parseInt(st.nextToken()); // 최대 층
        S = Integer.parseInt(st.nextToken()); // 현재 위치
        G = Integer.parseInt(st.nextToken()); // 가야할 위치
        U = Integer.parseInt(st.nextToken()); // 위로
        D = Integer.parseInt(st.nextToken()); // 아래로
        dp = new int[F+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[S] = 0;
        moveToStartLink(S, 0);
        
        if(dp[G] != Integer.MAX_VALUE) 
            System.out.println(dp[G]);
        else 
            System.out.println("use the stairs");
    }
}