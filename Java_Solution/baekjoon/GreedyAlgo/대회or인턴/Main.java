import java.io.*;

/**
 * Main
 */
public class Main {

    static public void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input[] = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]); // 여
        int M = Integer.parseInt(input[1]); // 남
        int K = Integer.parseInt(input[2]); // 인턴쉽

        while(true) {
            if(K<=0) break;
            if(M*2 < N) {
                N -= 1;
                K -= 1;
            } else {
                M -= 1;
                K -= 1;
            }
        }

        if(M*2 <= N) {
            System.out.println(M);
        } else {
            System.out.println(N/2);
        }
    }
}