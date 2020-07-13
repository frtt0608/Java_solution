import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long input = Long.parseLong(br.readLine());

        long S = input*2;
        long N = (long)Math.sqrt(S);
        
        while(true) {
            if(N*(N+1) > S) N-= 1;
            else break;
        }
        
        System.out.println(N);
    }
}