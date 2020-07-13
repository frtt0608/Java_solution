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
        int M = Integer.parseInt(input[1]);
        int cnt = 0;

        if(N>M)
            N %= M;

        while(true) {
            M -= N;
            if(M==0) break;
            
            cnt += N;
            N %= M;

            if(N==0 || M==1) break;
            else if(N==1) {
                cnt += M-1;
                break;
            }
        }
        

        System.out.println(cnt);
    }
}