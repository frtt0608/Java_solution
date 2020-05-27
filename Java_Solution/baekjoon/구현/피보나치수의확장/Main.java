import java.io.*;

/**
 * Main
 */
public class Main {
    static int N;

    static public void main(String args[]) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int type = 1;
        
        if(N<0) {
            N = -N;
            if(N%2 == 0) {
                type = -1;
            }
        } else if(N == 0) {
            type = 0;
        }
        
        int res = 0;
        int num0 = 1; // N이 1일때, N-2
        int num1 = 0; // N이 1일때, N-1
        
        for(int i=0; i<N; i++) {
            res = (num0+num1) % 1000000000;
            num0 = num1;
            num1 = res;
        }

        System.out.println(type);
        System.out.println(res);
    }
}