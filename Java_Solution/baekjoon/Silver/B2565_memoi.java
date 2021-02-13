import java.io.*;
import java.util.*;

public class B2565_memoi {

    static int N;
    static int[][] map;
    static int[] dp;

    public static void setDP() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<i; j++) {
                if(map[j][1] < map[i][1] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        map = new int[N][2];
        dp = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i] = 1;
            for(int j=0; j<2; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(map, new Comparator<int[]>(){
            @Override
            public int compare(int[] arr1, int[] arr2) {
                return arr1[0] - arr2[0];
            }
        });

        setDP();
        System.out.println(N - Arrays.stream(dp).max().getAsInt());
    }
}
