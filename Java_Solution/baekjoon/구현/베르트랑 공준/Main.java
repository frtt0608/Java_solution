import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean visited[] = new boolean[123456*2+1];
        int size = (int)Math.sqrt(123456*2+1);
        int target = 0;

        int n = 0;
        int cnt = 0;

        for(int i=2; i<size; i++) {
            target = i*2;
            while(target <= 123456*2) {
                visited[target] = true;
                target += i;
            }
        }

        while(true) {
            n = Integer.parseInt(br.readLine());
            cnt = 0;

            if(n==0) break;
            else {
                for(int i = n+1; i <= 2*n; i++) {
                    if(!visited[i]) cnt++;
                }
                System.out.println(cnt);
            }
        }
    }
}