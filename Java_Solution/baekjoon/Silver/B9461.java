import java.io.*;

public class B9461 {

    static final int SIZE = 100;
    static int N;
    static long[] dp;
    
    public static void setDP() {
        dp = new long[SIZE+1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for(int i=4; i<SIZE+1; i++) {
            dp[i] = dp[i-3] + dp[i-2];
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        setDP();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            System.out.println(dp[N]);
        }
    }
}
