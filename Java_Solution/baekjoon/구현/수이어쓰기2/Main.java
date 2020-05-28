import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        long cnt = 0;
        long target = -1;
        long temp = 0;

        for(int i=1; i<=N; i++) {
            cnt += (int)Math.log10(i) + 1;
            if(cnt >= K) {
                int diff = 0;
                target = i%10;
                temp = i/10;
                while(diff+K != cnt) {
                    diff++;
                    target = temp%10;
                    temp /= 10;
                }
                
                break;
            }
        }
        
        System.out.println(target);
    }
}