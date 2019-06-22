import java.io.*;
import java.util.*;

public class S6913 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("input.txt"));

        int T = Integer.parseInt(in.readLine());
        for(int tc=0; tc<T; tc++) {
            String[] nm = in.readLine().split(" ");
            
            int N = Integer.parseInt(nm[0]);
            int M = Integer.parseInt(nm[1]);

            String[][] data = new String[N][M];
            int[] sum = new int[N];
            int first = 0;
            int cnt = 0;

            for(int i=0; i<N; i++) {
                data[i] = in.readLine().split(" ");
                for(int j=0; j<M; j++) {
                    sum[i] += Integer.parseInt(data[i][j]);
                }
                if(first < sum[i]) {
                    first = sum[i];
                    cnt = 1;
                }
                else if(first == sum[i]) {
                    cnt += 1;
                }
            }
            System.out.println("#" + (tc+1) + " " + (cnt) + " " + (first));
        }
    }
}