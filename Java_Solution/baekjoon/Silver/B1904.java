import java.io.*;

public class B1904 {

    static final int MODE = 15746;
    static final int SIZE = 1000000;
    static int N;
    static int[] dp;

    public static void setDP() {
        dp = new int[SIZE+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<SIZE+1; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%MODE;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        setDP();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;
        
        // f(n) = f(n-1) + f(n-2);
        N = Integer.parseInt(br.readLine());
        System.out.println(dp[N]);
    }
}
