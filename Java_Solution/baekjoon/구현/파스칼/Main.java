import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int cnt = 0;
        for(int i=2; i<=Math.sqrt(N); i++) {
            if(N%i==0) {
                cnt = N - N/i;
                break;
            }
        }
        if(cnt==0) System.out.println(N-1);
        else System.out.println(cnt);
    }
}