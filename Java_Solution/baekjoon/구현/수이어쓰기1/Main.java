import java.util.*;
import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int N = Integer.parseInt(input);
        int cnt = 0;

        if(N <= 9) {
            cnt += N;    
        } else {
        // 9, 90, 900 ...
            int temp = 9;
            int idx = 1;
            while(idx < input.length()) {
                cnt += temp*idx;
                temp *= 10;
                idx += 1;
            }
            String nine = "";
            for(int i=0; i<input.length()-1; i++) {
                nine += "9";
            }
            cnt += idx*(N-Integer.parseInt(nine));
        }
        
        System.out.println(cnt);
    }
}