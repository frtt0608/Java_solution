import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] income = new int[N];
            int totalIncome = 0;
            
            String[] input = br.readLine().split(" ");
            for(int i=0; i<N; i++) {
                income[i] = Integer.parseInt(input[i]);
                totalIncome += income[i];
            }

            int avgIncome = totalIncome/N;
            int result = 0;

            for(int i=0; i<N; i++) {
                if(income[i] <= avgIncome) {
                    result += 1;
                }
            }

            System.out.println("#" + tc + " " + result);
        }
    }    
}
