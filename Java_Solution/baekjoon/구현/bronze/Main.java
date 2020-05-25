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
        int res = 1;

        while(N > 0) {
            res *= N;
            N -= 1;
        }

        System.out.println(res);
    }
}