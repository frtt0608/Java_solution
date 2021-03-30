import java.io.*;
import java.util.*;

public class B12852 {
    static int N;
    static int[] minRoute, preNum;
    static StringBuilder sb;

    public static void bottom_up() {

        for(int i=2; i<N+1; i++) {
            minRoute[i] = minRoute[i-1] + 1;
            preNum[i] = i-1;

            if(i%2 == 0 && minRoute[i] > minRoute[i/2]+1) {
                minRoute[i] = minRoute[i/2]+1;
                preNum[i] = i/2;
            }

            if(i%3 == 0 && minRoute[i] > minRoute[i/3]+1) {
                minRoute[i] = minRoute[i/3]+1;
                preNum[i] = i/3;
            }
        }
    }

    public static void appendIncludedNumber(int num) {
        
        while(num >= 1) {
            sb.append(num+" ");
            num = preNum[num];
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        minRoute = new int[N+1];
        preNum = new int[N+1];
    
        bottom_up();
        sb.append(minRoute[N] + "\n");
        appendIncludedNumber(N);
        System.out.println(sb.toString());
    }
}
