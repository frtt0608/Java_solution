import java.io.*;
import java.util.*;

public class B11653 {

    static final int MAX = 10000000;

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int i=2; i<=Math.sqrt(MAX); i++) {
            while(N%i == 0) {
                sb.append(i).append('\n');
                N /= i;
            }
        }

        if(N != 1) {
            sb.append(N);
        }

        System.out.println(sb.toString());
    }
}
