import java.util.*;
import java.io.*;

public class Main {
    static int N, M, maxCnt;
    
    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        if(N==1 || M==1) {
            maxCnt = 1;
        } else if(N == 2) {
            maxCnt = Math.min(4, (M+1)/2);
        } else if(M < 7) {
            maxCnt = Math.min(4, M);
        } else {
            maxCnt = M-2;
        }

        System.out.println(maxCnt);
    }
}