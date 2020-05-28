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
        boolean visited[] = new boolean[1001];

        int cnt = 0;
        int target = 0;

        loop:
        for(int i=2; i<=N; i++) {
            if(visited[i]) continue;
            target = i;
            while(target <= N) {
                if(visited[target]) {
                    target += i;
                    continue;
                }
                
                cnt += 1;
                visited[target] = true;
                if(cnt==K) {
                    System.out.println(target);
                    break loop;
                }
                target += i;
            }
        }
    }
}